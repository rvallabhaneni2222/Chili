/**
 * System Soft Technolgies Copyright (C) 2013 ayalamanchili@sstech.mobi
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.audit;

import info.chili.commons.ReflectionUtils;
import info.chili.hibernate.envers.AuditRevisionEntity;
import info.chili.service.jrs.types.Entries;
import info.chili.service.jrs.types.Entry;
import info.chili.spring.SpringContext;
import info.chili.service.jrs.types.EntityAuditDataTbl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author anuyalamanchili
 */
@Component
@Scope("prototype")
public class AuditService {

    protected AuditReader auditReader;
    //TODO get this only on demand
    @PersistenceContext
    protected EntityManager em;

    public AuditReader getAuditReader() {
        if (auditReader == null) {
            auditReader = AuditReaderFactory.get(em);
        }
        return auditReader;
    }

    public Object getVersion(Class cls, Long id, Integer version) {
        List<Number> revNumbers = getAuditReader().getRevisions(cls, id);
        if (revNumbers.size() >= version) {
            return getAuditReader().find(cls, id, revNumbers.get(revNumbers.size() - version));
        } else {
            return null;
        }
    }
//TODO is this getting the current or second recent?

    public Object mostRecentVersion(Class cls, Long id) {
        List<Number> revNumbers = getAuditReader().getRevisions(cls, id);
        if (revNumbers.size() > 1) {
            return getAuditReader().find(cls, id, revNumbers.get(revNumbers.size() - 2));
        } else {
            return null;
        }
    }

    public String tableWithRecentChanges(Object entity, Long id, String... ignoreFields) {
        StringBuilder changesTable = new StringBuilder();
        changesTable.append("<table>");
        changesTable.append("<tr>");
        changesTable.append("<td>");
        changesTable.append("Field");
        changesTable.append("</td>");
        changesTable.append("<td>");
        changesTable.append("Old Value");
        changesTable.append("</td>");
        changesTable.append("<td>");
        changesTable.append("New Value");
        changesTable.append("</td>");
        changesTable.append("</tr>");
        for (AuditChangeDto dto : compareWithRecentVersion(entity, id, ignoreFields)) {
            changesTable.append("<tr>");
            changesTable.append("<td>");
            changesTable.append(dto.getPropertyName());
            changesTable.append("</td>");
            changesTable.append("<td>");
            changesTable.append(dto.getOldValue());
            changesTable.append("</td>");
            changesTable.append("<td>");
            changesTable.append(dto.getNewValue());
            changesTable.append("</td>");
            changesTable.append("</tr>");
        }
        changesTable.append("</table>");
        return changesTable.toString();
    }

    public List<AuditChangeDto> compareWithRecentVersion(Object entity, Long id, String... ignoreFields) {
        List<AuditChangeDto> changes = new ArrayList();
        Object previousVersion = AuditService.instance().getVersion(entity.getClass(), id, 1);
        if (previousVersion == null) {
            return changes;
        }
        Map<String, Object> valuesMap = ReflectionUtils.getFieldsDataFromEntity(entity, entity.getClass(), true);
        Map<String, Object> previousValuesMap = ReflectionUtils.getFieldsDataFromEntity(previousVersion, entity.getClass(), true);
        for (Map.Entry<String, Object> entry : valuesMap.entrySet()) {
            if (Arrays.asList(ignoreFields).contains(entry.getKey())) {
                continue;
            }
            if (previousValuesMap.get(entry.getKey()) == null && entry.getValue() != null) {
                AuditChangeDto dto = new AuditChangeDto();
                dto.setPropertyName(entry.getKey());
                dto.setOldValue("");
                dto.setNewValue(entry.getValue().toString());
                changes.add(dto);
            }
            if (previousValuesMap.get(entry.getKey()) != null && entry.getValue() == null) {
                AuditChangeDto dto = new AuditChangeDto();
                dto.setPropertyName(entry.getKey());
                dto.setOldValue(previousValuesMap.get(entry.getKey()).toString());
                dto.setNewValue("");
                changes.add(dto);
            }
            if (previousValuesMap.get(entry.getKey()) != null && entry.getValue() != null && !previousValuesMap.get(entry.getKey()).toString().equals(entry.getValue().toString())) {
                AuditChangeDto dto = new AuditChangeDto();
                dto.setPropertyName(entry.getKey());
                dto.setOldValue(previousValuesMap.get(entry.getKey()).toString());
                dto.setNewValue(entry.getValue().toString());
                changes.add(dto);
            }
        }
        return changes;
    }

    //get recent changes on a entity
    public EntityAuditDataTbl getRecentChanges(String className, Long id, List<String> ignoreFields) {
        ignoreFieldsList.addAll(ignoreFields);
        EntityAuditDataTbl table = new EntityAuditDataTbl();
        Class entityCls;
        try {
            entityCls = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Invalid Class Name", ex);
        }
        Map<String, Object> previousValuesMap = null;
        for (Number revNumber : getAuditReader().getRevisions(entityCls, id)) {
            Entries auditData = new Entries();
            AuditRevisionEntity revEntity = getAuditReader().findRevision(AuditRevisionEntity.class, revNumber);
            auditData.addEntry(new Entry("UPDATED-BY", revEntity.getUpdatedUserId()));
            auditData.addEntry(new Entry("UPDATED-AT", revEntity.getUpdatedTimeStamp().toString()));
            Object entity = getAuditReader().find(entityCls, id, revNumber);
            if (entity == null) {
                continue;
            }
            Map<String, Object> valuesMap = ReflectionUtils.getFieldsDataFromEntity(entity, entityCls, true);
            for (Map.Entry<String, Object> entry : valuesMap.entrySet()) {
                if (ignoreFields.contains(entry.getKey())) {
                    continue;
                }
                Entry e = new Entry();
                e.setId(entry.getKey());
                if (entry.getValue() != null) {
                    e.setValue(entry.getValue().toString());
                    checkForChanges(entry, e, previousValuesMap);
                } else {
                    e.setValue("");
                }
                auditData.addEntry(e);
            }
            table.addAuditData(auditData);
            previousValuesMap = valuesMap;
        }
        return table;
    }

    public static void checkForChanges(Map.Entry<String, Object> entry, info.chili.service.jrs.types.Entry e, Map<String, Object> previousValuesMap) {
        if (null != previousValuesMap) {
            if (previousValuesMap.get(entry.getKey()) == null && entry.getValue() != null) {
                highLightChanges(e);
            }
            if (previousValuesMap.get(entry.getKey()) != null && entry.getValue() == null) {
                highLightChanges(e);
            }
            if (previousValuesMap.get(entry.getKey()) != null && entry.getValue() != null && !previousValuesMap.get(entry.getKey()).toString().equals(entry.getValue().toString())) {
                highLightChanges(e);
            }
        }
    }

    protected static void highLightChanges(info.chili.service.jrs.types.Entry e) {
        e.setValue("<font style=\"BACKGROUND-COLOR: yellow\">" + e.getValue() + "</font>");
    }

    public static String highLightChanges(String str) {
        return "<font style=\"BACKGROUND-COLOR: yellow\">" + str + "</font>";
    }
    protected static final List<String> ignoreFieldsList = new ArrayList<String>();

    static {
        ignoreFieldsList.add("id");
        ignoreFieldsList.add("version");
    }

    public static AuditService instance() {
        return SpringContext.getBean(AuditService.class);
    }
}

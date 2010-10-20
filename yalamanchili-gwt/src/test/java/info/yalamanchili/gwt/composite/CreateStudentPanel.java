package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.entity.Student;
import info.yalamanchili.gwt.fields.DataType;

public class CreateStudentPanel extends CreateComposite<Student> {
	public CreateStudentPanel(CreateCompositeType type) {
		super(type);
		initCreateComposite(Student.class.getName(), null);
	}

	@Override
	protected void addButtonClicked() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createButtonClicked() {
	}

	@Override
	protected Student populateEntityFromFields() {
		Student student = new Student();
		student.setFirstName(getStringField("firstName"));
		return student;
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addWidgets() {
		addField("firstName", false, DataType.STRING_FIELD);

	}

	@Override
	protected void addWidgetsBeforeCaptionPanel() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	}

}

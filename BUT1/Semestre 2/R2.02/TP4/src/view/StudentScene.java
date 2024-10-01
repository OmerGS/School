package view;
import javafx.scene.layout.BorderPane;
import controller.Controller;
import controller.StudentFileAccess;
import javafx.geometry.Insets;

/**
 * The StudentScene class represents the main scene of the application, containing a student table,
 * a student form, and a file toolbar.
 */
public class StudentScene extends BorderPane {

   private StudentForm studentForm; // Student form for adding and editing student information
   private StudentTable studentTable; // Student table for displaying student data
   private FileToolBar fileToolBar; // File toolbar for file-related operations

   /**
    * Constructs a StudentScene object.
    * @param controller The controller managing the scene's functionality
    * @param studentFileAccess The file access object for student data
    */
   public StudentScene(Controller controller, StudentFileAccess studentFileAccess) {

      // Initialize student table, student form, and file toolbar
      this.studentTable = new StudentTable(studentFileAccess, controller);
      this.fileToolBar = new FileToolBar(controller);
      this.studentForm = new StudentForm(controller);

      // Set margins and positions for the components within the border pane
      BorderPane.setMargin(studentTable, new Insets(50, 50, 50, 50));
      this.setCenter(studentTable);

      this.setRight(studentForm);
      BorderPane.setMargin(studentForm, new Insets(50, 50, 50, 50));
      this.setTop(fileToolBar);

      BorderPane.setMargin(fileToolBar, new Insets(50, 50, 50, 50));
   }

   /**
    * Retrieves the file toolbar associated with the student scene.
    * @return The file toolbar
    */
   public FileToolBar getFileToolBar() {
      return fileToolBar;
   }

   /**
    * Retrieves the student table associated with the student scene.
    * @return The student table
    */
   public StudentTable getStudentTable() {
      return studentTable;
   }

   /**
    * Retrieves the student form associated with the student scene.
    * @return The student form
    */
   public StudentForm getStudentForm() {
      return studentForm;
   }
}

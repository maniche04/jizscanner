/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.models;


import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.DepthTest;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import jizanscanner.controllers.ScannerGrnSetupController;
import static jizanscanner.controllers.ScannerGrnSetupController.allGrnRow;
import org.controlsfx.control.PropertySheet.Item;

/** 
 *
 * @author santosh
 */

public class EditingCell  extends TableCell<GrnDetails, String> {

      private TextField textField;
      int gd = 0;
      int bx = 0;
      int leak = 0;
      int brk = 0;
      
     GrnDetails grd =  new GrnDetails();
     private static ObservableList<GrnDetails> Grnrow;
     

    int currentIndex = 0;
     
    public EditingCell() {
    
    }

    @Override
    public void startEdit() {
        super.startEdit();
       if (textField == null) {
            createTextField();
        }
        setGraphic(textField);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                textField.requestFocus();
                textField.selectAll();
            }
        });
       }

       @Override
       public void cancelEdit() {
           super.cancelEdit();
           setText((String) getItem());
           setContentDisplay(ContentDisplay.TEXT_ONLY);
          
         }

       @Override
       public void updateItem(String item, boolean empty) {
       super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setGraphic(textField);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            } else {
                setText(getString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);
               
                  }
               }
          }
       
        

        private void createTextField() {
          textField = new TextField(getString());
          textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
           
          textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                System.out.println("this is key"+t.getCode());
              
                if (t.getCode() == KeyCode.ENTER ) {
                    
                    commitEdit(textField.getText());
                    getTableView().refresh();
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                   
                } else if (t.getCode() == KeyCode.TAB || t.getCode() == KeyCode.RIGHT) {
                    System.out.println("this is right key");
                                       
                    //System.out.println("test"+ScannerGrnSetupController.allGrnRow.get(getTableRow().getIndex()).getBox());
                
                    commitEdit(textField.getText());
                    getTableView().refresh();
                    int row = getTableRow().getIndex();
                    int column = getTableView().getColumns().indexOf(getTableColumn());
                    TableColumn rightcol = getTableView().getColumns().get(column + 1);
                    getTableView().getSelectionModel().select(row,rightcol);
                    getTableView().requestFocus();
                    getTableView().edit(row, rightcol);
                    TableColumn nextColumn = getNextColumn(!t.isShiftDown());
                    
                    if (nextColumn != null) {

                    //getTableView().edit(row, nextColumn);
                    //getTableView().refresh(); 
                        
                    //getTableView().getSelectionModel().select(row, rightcol);
                    //getTableView().requestFocus();
                    //getTableView().getFocusModel().focus(row, rightcol);
                        
                         }
                    
                     }else if(t.getCode() == KeyCode.LEFT)
                      {
                         
                    commitEdit(textField.getText());
                      
                    getTableView().refresh();  
                    int row = getTableRow().getIndex();
                    int column = getTableView().getColumns().indexOf(getTableColumn());
                    TableColumn rightcol = getTableView().getColumns().get(column + 1);
                    getTableView().getSelectionModel().select(row,rightcol);
                    getTableView().requestFocus();
                    getTableView().edit(row, rightcol);
                    
                      TableColumn nextColumn = getNextColumn(t.isShiftDown());  
                      if (nextColumn != null) {
                        getTableView().edit(getTableRow().getIndex(), nextColumn);
                      }
                     }else if(t.getCode() == KeyCode.DOWN)
                       {  
                      commitEdit(textField.getText());
                    getTableView().refresh();   
                    int row = getTableRow().getIndex()+1;
                    int column = getTableView().getColumns().indexOf(getTableColumn());
                    TableColumn samecol = getTableView().getColumns().get(column);
                    getTableView().getSelectionModel().select(row,samecol);
                    getTableView().requestFocus();
                    getTableView().edit(row, samecol);
                      
                      
                      TableColumn nextColumn = getUpDownColumn(t.isShiftDown()); 
                      
                      if (nextColumn != null) {
                          //to check the navigation will happen only with in data row not out side
                          if(getTableRow().getIndex() < getTableView().getItems().size()-1)
                          { 
                              
                        getTableView().edit(getTableRow().getIndex()+1, nextColumn);
                        //to check if it last row data then it should move to top row
                        
                        }else if (getTableRow().getIndex() == getTableView().getItems().size()-1)
                         {
                              
                            getTableView().edit(0, nextColumn);
                            getTableView().accessibleTextProperty().setValue(null);
                                
                         }
                      }
                      
                   }else if(t.getCode() == KeyCode.UP)
                      {   
                        commitEdit(textField.getText());
                        getTableView().refresh(); 
                        int row = getTableRow().getIndex()-1;
                        int column = getTableView().getColumns().indexOf(getTableColumn());
                        TableColumn samecol = getTableView().getColumns().get(column);
                        getTableView().getSelectionModel().select(row,samecol);
                        getTableView().requestFocus();
                        getTableView().edit(row, samecol);
                     
                        TableColumn nextColumn = getUpDownColumn(t.isShiftDown());  
                        if (nextColumn != null) {
                            //to check if it first row then it should navigate to last row
                         if(getTableRow().getIndex() == 0)
                            {
                         getTableView().edit(getTableView().getItems().size()-1, nextColumn);
                            }
                         else
                            {
                         //to navigate down to the rows
                         getTableView().edit(getTableRow().getIndex()-1, nextColumn);
                            }
                          } 
                      }else if(t.getCode() == KeyCode.NUMPAD1)
                        {
                       System.out.println("t num keys are pressed!");
                        }
                     }
                  });
        
            textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
          @Override
          public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if(!newValue && textField != null){
                commitEdit(textField.getText());
              }
            }
          });
            
         }

            private String getString() {
            return getItem() == null ? "" : getItem().toString();
         }
               
        /**  
         * @param forward true gets the column to the right, false the column to the left of the current column
         * @return
         */
        
        
             private TableColumn<GrnDetails, ?> getNextColumn(boolean forward) {
      
             List<TableColumn<GrnDetails, ?>> columns = new ArrayList<>();
             for (TableColumn<GrnDetails, ?> column : getTableView().getColumns()) {
                columns.addAll(getLeaves(column));
             }
        
             //There is no other column that supports editing.
             if (columns.size() < 2) {
                return null;
             }
        
             int currentIndex = columns.indexOf(getTableColumn());
             int nextIndex = currentIndex;
             if (forward) {
                nextIndex++;
                if (nextIndex > columns.size() - 1) {
                 nextIndex = 0;
              }
            } else {
                nextIndex--;
             if (nextIndex < 0) {
                nextIndex = columns.size() - 1;
                }
             }
             return columns.get(nextIndex);
    }

    
     private TableColumn<GrnDetails, ?> getUpDownColumn(boolean forward) {
         List<TableColumn<GrnDetails, ?>> columns = new ArrayList<>();    
         
         for(TableColumn<GrnDetails, ?> column : getTableView().getColumns()) {
             columns.addAll(getLeaves(column));
         }

         //There is no other column that supports editing.
         if (columns.size() < 2) {
              return null;
         }
         
         currentIndex = columns.indexOf(getTableColumn());        
         return columns.get(currentIndex);     
     }

     
     private List<TableColumn<GrnDetails, ?>> getLeaves(TableColumn<GrnDetails, ?> root) {
       List<TableColumn<GrnDetails, ?>> columns = new ArrayList<>();
       if(root.getColumns().isEmpty()) {
            //We only want the leaves that are editable.
       if(root.isEditable()){
           columns.add(root);
             }
            return columns;
        }else {
            for(TableColumn<GrnDetails, ?> column : root.getColumns()) {
              columns.addAll(getLeaves(column));          
                }
        return columns;
         }
     }

  
       
    
    

  public int getCurrentRow()
     {      
        
         return this.currentIndex;
        
     }
}

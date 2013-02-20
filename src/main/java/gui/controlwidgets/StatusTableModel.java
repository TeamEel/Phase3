/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controlwidgets;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author James
 */
class StatusTableModel extends AbstractTableModel implements TableModel {

    private final int size = 7;
    public StatusRow[] rows = new StatusRow[7];

    public StatusTableModel() {
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return size;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 0) {
            return rows[row].key();
        }
        if (col == 1) {
            return rows[row].value();
        }
        return null;
    }
}

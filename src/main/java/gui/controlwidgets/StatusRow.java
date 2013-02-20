/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controlwidgets;

/**
 *
 * @author James
 */
class StatusRow {

    public String key;
    public Double value;

    public StatusRow(String key, Double initialValue) {
        this.key = key;
        this.value = initialValue;
    }

    public void update(Double newValue) {
        this.value = newValue;
    }

    public String key() {
        return this.key;
    }

    public String value() {
        return String.format("%.03f",this.value);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
    
    public void Update(Double newValue) {
        this.value = newValue;
    }
    
    public String Key() {
        return this.key;
    }
    
    public Double Value() {
        return this.value;
    }
}

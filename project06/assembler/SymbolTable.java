// SymbolTable.java
// Keeps a correspondence between symbolic labels and
// numeric addresses

package assembler;

import java.util.Hashtable;

public class SymbolTable {

  private Hashtable<String, String> table;

  // constructor
  public SymbolTable() {
    // initializes the hash table to a size of 10 with a collision factor of .75
    table = new Hashtable<String, String>(10, (float)0.75);
    table.put("SP", "0000000000000000");
    table.put("LCL", "0000000000000001");
    table.put("ARG", "0000000000000010");
    table.put("THIS", "0000000000000011");
    table.put("THAT", "0000000000000100");

    table.put("R0", "0000000000000000");
    table.put("R1", "0000000000000001");
    table.put("R2", "0000000000000010");
    table.put("R3", "0000000000000011");
    table.put("R4", "0000000000000100");
    table.put("R5", "0000000000000101");
    table.put("R6", "0000000000000110");
    table.put("R7", "0000000000000111");
    table.put("R8", "0000000000001000");
    table.put("R9", "0000000000001001");
    table.put("R10","0000000000001010");
    table.put("R11","0000000000001011");
    table.put("R12","0000000000001100");
    table.put("R13","0000000000001101");
    table.put("R14","0000000000001110");
    table.put("R15","0000000000001111");

    table.put("SCREEN", "0100000000000000");
    table.put("KBD", "0110000000000000");
  }

  // adds the pair (symbol, address) to the table
  public void addEntry(String symbol, String address) {
    table.put(symbol, address);
  }

  // checks if the symbol table contains a given symbol
  public Boolean contains(String symbol) {
    return table.containsKey(symbol);
  }

  // returns the address associated with a given symbol
  public String getAddress(String symbol) {
    return table.get(symbol);
  }

}
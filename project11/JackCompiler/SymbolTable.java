//SymbolTable.java

package JackCompiler;
import java.util.*;


public class SymbolTable{
	
	public Integer static_counter = 0;
	public Integer field_counter = 0;
	public Integer arg_counter = 0;
	public Integer var_counter = 0;
	
	public enum Kind {
	    STATIC, FIELD, ARG, VAR;
	}
	
	public class Entry {
		String type;
		Kind kind;
		Integer index;
	}
	
	//Declare three hashmaps - one for each command type
	public HashMap<String, Entry> symbols;
	public HashMap<String, Entry> classSymbols;

	//creates a new empty symbol table
	public SymbolTable()
	{
		symbols = new HashMap<String, Entry>();
		classSymbols = new HashMap<String, Entry>();
	}
	
	//Starts a new subroutine scope
	public void startSubroutine(){
		symbols.clear();
		arg_counter = 0;
		var_counter = 0;
		
	}
	
	//Defines a new identifier of a given name, type, and kind and assigns it a running index
	public void Define(String name, String type, Kind k, Boolean classLevel){
		Entry e = new Entry();
		e.type = type;
		e.kind = k;
		if(k == Kind.STATIC) {
			e.index = static_counter;
			static_counter++;
		}
		if(k == Kind.FIELD) {
			e.index = field_counter;
			field_counter++;
		}
		if(k == Kind.ARG) {
			e.index = arg_counter;
			arg_counter++;
		}
		if(k == Kind.VAR) {
			e.index = var_counter;
			var_counter++;
		}
		
		if (classLevel){
			classSymbols.put(name, e);
		} else {
			symbols.put(name, e);
		}
		
	}
	
	//Returns the number of variable of the given kind already defined in the current scope
	public int VarCount(Kind k) {
		if(k == Kind.STATIC) {
			return static_counter;
		}else if(k == Kind.FIELD) {
			return field_counter;
		}else if(k == Kind.ARG) {
			return arg_counter;
		}else if(k == Kind.VAR) {
			return var_counter;
		}else {
			return 0;
		}
	}
	
	//Returns the kind of the named identifier in the current scope
	public Kind KindOf(String name, Boolean classLevel) {
		if (classLevel){
			return classSymbols.get(name).kind;
		} else {
			return symbols.get(name).kind;
		}
	}	
	
	//Returns the type of the named identifier in the current scope
	public String TypeOf(String name, Boolean classLevel) {
		if (classLevel){
			return classSymbols.get(name).type;
		} else {
			return symbols.get(name).type;
		}
	}
	
	//Returns the index assigned to the name identifier
	public int IndexOf(String name, Boolean classLevel){
		if (classLevel){
			return classSymbols.get(name).index;
		} else {
			return symbols.get(name).index;
		}
	}
}
package com.tigerit.exam;


import static com.tigerit.exam.IO.*;
import java.util.*;

/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
class ColumnInfo {
	Integer clIndex;
	String tableName;

	public ColumnInfo(Integer clIndex, String tableName) {
		super();
		this.clIndex = clIndex;
		this.tableName = tableName;
	}

	public Integer getClIndex() {
		return clIndex;
	}

	public void setClIndex(Integer clIndex) {
		this.clIndex = clIndex;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String toString() {
		return "ColumnInfo [clIndex=" + clIndex + ", tableName=" + tableName + "]";
	}
}

class ResultRow {
	int t1R;
	int t2R;

	public ResultRow(int t1r, int t2r) {
		super();
		this.t1R = t1r;
		this.t2R = t2r;
	}

	public int getT1R() {
		return t1R;
	}

	public void setT1R(int t1r) {
		t1R = t1r;
	}

	public int getT2R() {
		return t2R;
	}

	public void setT2R(int t2r) {
		t2R = t2r;
	}

	@Override
	public String toString() {
		return "ResultRow [t1R=" + t1R + ", t2R=" + t2R + "]";
	}
}

class Table {

	String name;
	ArrayList<String> columnNames;
	ArrayList<ArrayList<Integer>> records;
	int totalRecords, totalColumns;
	int recordCounter;

	Table(String name, int totalColumns) {
		this.name = name;
		this.columnNames = new ArrayList<>(totalColumns);
		this.records = new ArrayList<ArrayList<Integer>>();
		this.totalColumns = totalColumns;
	}

	Table(String name, int totalColumns, int totalRecords) {
		this.name = name;
		this.columnNames = new ArrayList<>(totalColumns);
		this.records = new ArrayList<ArrayList<Integer>>();
		this.totalColumns = totalColumns;
		this.totalRecords = totalRecords;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(ArrayList<String> columnNames) {
		this.columnNames = columnNames;
	}

	public ArrayList<ArrayList<Integer>> getRecords() {
		return records;
	}

	public void setRecords(ArrayList<ArrayList<Integer>> records) {
		this.records = records;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getRecordCounter() {
		return recordCounter;
	}

	public void setRecordCounter(int recordCounter) {
		this.recordCounter = recordCounter;
	}

	public int getTotalColumns() {
		return totalColumns;
	}

	public void setTotalColumns(int totalColumns) {
		this.totalColumns = totalColumns;
	}

	void setColumnNames(String names) {
		String cNames[] = names.split("[ ]+");
		for (int i = 0; i < totalColumns; i++) {
			columnNames.add(cNames[i]);
		}
	}

	void addRecord(String columnValues) {
		ArrayList<Integer> record = new ArrayList<>();
		String values[] = columnValues.split("[ ]+");
		for (int i = 0; i < totalColumns; i++) {
			record.add(Integer.parseInt(values[i]));
		}
		records.add(record);
		recordCounter++;
	}

	public String printData() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < columnNames.size(); i++) {
			str.append(columnNames.get(i));
			if (i == columnNames.size() - 1)
				continue;
			str.append(" ");
		}
		str.append("\n");
		for (int i = 0; i < records.size(); i++) {
			for (int j = 0; j < records.get(i).size(); j++) {
				str.append(records.get(i).get(j).toString());
				if (j == records.get(i).size() - 1)
					continue;
				str.append(" ");
			}
			str.append("\n");
		}
		return str.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnNames == null) ? 0 : columnNames.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + recordCounter;
		result = prime * result + ((records == null) ? 0 : records.hashCode());
		result = prime * result + totalColumns;
		result = prime * result + totalRecords;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Table other = (Table) obj;
		if (columnNames == null) {
			if (other.columnNames != null)
				return false;
		} else if (!columnNames.equals(other.columnNames))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (recordCounter != other.recordCounter)
			return false;
		if (records == null) {
			if (other.records != null)
				return false;
		} else if (!records.equals(other.records))
			return false;
		if (totalColumns != other.totalColumns)
			return false;
		if (totalRecords != other.totalRecords)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Table [name=" + name + ",\ncolumnNames=" + columnNames + ",\nrecords=" + printData()
				+ ",\ntotalRecords=" + totalRecords + ",\ntotalColumns=" + totalColumns + ",\nrecordCounter="
				+ recordCounter + "]\n";
	}
}

public class Solution implements Runnable {
	Map<String, Table> tables;

	@Override
	public void run() {
		// Reading Number of Test cases
		int totalCase = readInt(readLine());
		// Taking input for every test case
		for (int testCase = 1; testCase <= totalCase; testCase++) {
			System.out.println("Test: " + testCase);
			// Reading number of tables
			int nT = readInt(readLine().trim());
			tables = new HashMap<>(nT);
			// Taking inputs for every table
			for (int ti = 0; ti < nT; ti++) {
				// Reading table name
				String name = readLine().trim();
				// Reading number of columns and number of records
				String tokens[] = readLine().split("[ ]+");
				int nC = readInt(tokens[0]);
				int nD = readInt(tokens[1]);
				Table table = new Table(name, nC, nD);
				// Reading column names and set to corresponding table
				table.setColumnNames(readLine());
				// Read and set records to Table
				for (int record = 0; record < nD; record++) {
					String values = readLine();
					table.addRecord(values);
				}
				// Add table to list of tables of test case;
				tables.put(name, table);
			}
			// Read number of Queries
			int nQ = readInt(readLine());
			//Read and process a single query of test case
			for (int q = 0; q < nQ; q++) {
				String line1 = readLine();
				String line2 = readLine();
				String line3 = readLine();
				String line4 = readLine();
				readLine();
				processSingleQuery(line1, line2, line3, line4);
			}
		}
	}

	private void processSingleQuery(String q1, String q2, String q3, String q4) {
		// System.out.println("\n" + q1 + "\n" + q2 + "\n" + q3 +"\n" + q4 + "\n");
		String q1Tokens[] = q1.split("[ ,]+");
		String q2Tokens[] = q2.split("[ ]+");
		String q3Tokens[] = q3.split("[ ]+");
		String q4Tokens[] = q4.split("[ ]+");
		String t1ShortName = null;
		String t2ShortName = null;
		if (q2Tokens.length > 2 || q3Tokens.length > 2) {
			t1ShortName = q2Tokens[2];
			t2ShortName = q3Tokens[2];
		}
		Table t1 = tables.get(q2Tokens[1]);
		Table t2 = tables.get(q3Tokens[1]);
		//System.out.println("Table 1:" + t1.toString());
		//System.out.println("Table 2:" + t2.toString());

		Table resultTable = null;
		ArrayList<String> resultColumns = new ArrayList<>();
		ArrayList<ColumnInfo> columnInfo = new ArrayList<>();
		if (q1Tokens[1].equals("*")) {
			resultTable = new Table("result", t1.getTotalColumns() + t2.getTotalColumns());
			for (String columnName : t1.getColumnNames()) {
				resultColumns.add(columnName);
				columnInfo.add(new ColumnInfo(findColumnIndex(t1, columnName), t1.getName()));

			}
			for (String columnName : t2.getColumnNames()) {
				resultColumns.add(columnName);
				columnInfo.add(new ColumnInfo(findColumnIndex(t2, columnName), t2.getName()));

			}
		} else {
			resultTable = new Table("result", q1Tokens.length - 1);
			for (int i = 1; i < q1Tokens.length; i++) {
				String clTokens[] = q1Tokens[i].split("\\.");
				resultColumns.add(clTokens[1]);
				if (clTokens[0].equals(t1ShortName)) {
					columnInfo.add(new ColumnInfo(findColumnIndex(t1, clTokens[1]), t1.getName()));
				} else {
					columnInfo.add(new ColumnInfo(findColumnIndex(t2, clTokens[1]), t2.getName()));
				}
			}
		}
		resultTable.setColumnNames(resultColumns);

		String t1Tokens[] = q4Tokens[1].split("\\.");
		String t2Tokens[] = q4Tokens[3].split("\\.");
		// find column indexes of conddition attributes;
		int c1 = findColumnIndex(t1, t1Tokens[1]);
		int c2 = findColumnIndex(t2, t2Tokens[1]);
		// System.out.println(t1.printData());
		// System.out.println(t2.printData());
		
		//sortMergeJoin returns row index mapping from table t1 and t2 for result table rows
		ArrayList<ResultRow> rowIndexes = sortMergeJoin(t1, t2, c1, c2);
		ArrayList<ArrayList<Integer>> resultRecords = new ArrayList<>();
		for (int i = 0; i < rowIndexes.size(); i++) {
			ArrayList<Integer> resultRow = new ArrayList<>();
			for (int j = 0; j < resultTable.getColumnNames().size(); j++) {
				Integer value;
				if (columnInfo.get(j).getTableName().equals(t1.getName())) {
					value = t1.getRecords().get(rowIndexes.get(i).getT1R()).get(columnInfo.get(j).getClIndex());
				} else {
					value = t2.getRecords().get(rowIndexes.get(i).getT2R()).get(columnInfo.get(j).getClIndex());
				}
				resultRow.add(value);
			}
			resultRecords.add(resultRow);
		}
		//Lexicographic sort of result table rows
		Collections.sort(resultRecords, new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> one, List<Integer> two) {
				for (Integer i = 0; i < one.size(); i++) {
					if (one.get(i) < two.get(i))
						return -1;
					if (one.get(i) > two.get(i))
						return 1;
				}
				return 0;
			}
		});
		resultTable.setRecords(resultRecords);
		//printing single query result
		System.out.println(resultTable.printData());
	}

	public int findColumnIndex(Table t, String cName) {
		return t.getColumnNames().indexOf(cName);
	}
 
	private ArrayList<ResultRow> sortMergeJoin(Table t1, Table t2, int c1, int c2) {
		ArrayList<ResultRow> rowIndexes = new ArrayList<>();
		int t1_i = 0;
		int t2_i = 0;
		// Sorting tables
		sortbyColumn(t1.getRecords(), c1);
		sortbyColumn(t2.getRecords(), c2);

		ArrayList<Integer> t1Row = t1.getRecords().get(t1_i);
		ArrayList<Integer> t2Row = t2.getRecords().get(t2_i);
		int t1_size = t1.getRecords().size();
		int t2_size = t2.getRecords().size();
		
		while ((t1Row != null || t1_i < t1_size) && (t2Row != null || t2_i < t2_size)) {
			int x = t1Row.get(c1).intValue();
			int y = t2Row.get(c2).intValue();
			if (x > y) {
				t2Row = (++t2_i >= t2_size) ? null : t2.getRecords().get(t2_i);
			} else if (x < y) {
				t1Row = (++t1_i >= t1_size) ? null : t1.getRecords().get(t1_i);
			} else {
				rowIndexes.add(new ResultRow(t1_i, t2_i));
				int t2_j = t2_i;
				while (++t2_j < t2_size && t1Row.get(c1).intValue() == t2.getRecords().get(t2_j).get(c2).intValue()) {
					rowIndexes.add(new ResultRow(t1_i, t2_j));
				}
				int t1_j = t1_i;
				while (++t1_j < t1_size && t1.getRecords().get(t1_j).get(c1).intValue() == t2Row.get(c2).intValue()) {
					rowIndexes.add(new ResultRow(t1_j, t2_i));
				}
				t1Row = (++t1_i >= t1_size) ? null : t1.getRecords().get(t1_i);
				t2Row = (++t2_i >= t2_size) ? null : t2.getRecords().get(t2_i);
			}
		}
		return rowIndexes;
	}
	// Function to sort by column
	public static void sortbyColumn(ArrayList<ArrayList<Integer>> records, int col) {
		Collections.sort(records, new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> one, List<Integer> two) {
				return one.get(col).compareTo(two.get(col));
			}
		});
	}

	public static int readInt(String str) {
		return Integer.parseInt(str.trim());
	}
}

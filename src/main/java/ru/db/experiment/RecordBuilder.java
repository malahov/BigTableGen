package ru.db.experiment;

import java.util.Date;

/**
 * Констуирует запись
 */
public class RecordBuilder {
	
	private String insertCode;
	
	public RecordBuilder(String insertCode) {
		this.insertCode = insertCode;
	}
	
	public Record buildRecord(int id) {
		Record res = new Record(id, insertCode);
		return res;
	}
	
	public static class Record {
		private static String template = "Тескст записи с id = ";
		
		private int id;
		private boolean bool;
		private String text;
		private String insCode;
		private Date createDateTime;
		private Date createDate;
		
		public Record(int id, String insCode) {
			this.id = id;
			bool = id % 2 == 0;
			text = template + id;
			this.insCode = insCode;
			createDateTime = new Date();
			createDate = createDateTime;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public boolean isBool() {
			return bool;
		}

		public void setBool(boolean bool) {
			this.bool = bool;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getInsCode() {
			return insCode;
		}

		public void setInsCode(String insCode) {
			this.insCode = insCode;
		}

		public Date getCreateDateTime() {
			return createDateTime;
		}

		public void setCreateDateTime(Date createDateTime) {
			this.createDateTime = createDateTime;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		
	}
}

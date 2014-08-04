package ua.edu.lp.sadiploma.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="OUTPUT_DATA")
public class OutputData extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2760008713023501484L;
	private Date startTime;
	private Date finishTime;
	private String resultNumbers;
	
	public OutputData() {
		
	}
	
	@ManyToOne
	@JoinColumn(name = "INPUT_DATA_ID")
	private InputData inputData;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getResultNumbers() {
		return resultNumbers;
	}

	public void setResultNumbers(String resultNumbers) {
		this.resultNumbers = resultNumbers;
	}

}

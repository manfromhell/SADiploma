package ua.edu.lp.sadiploma.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="INPUT_DATA")
public class InputData extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3975545484495454331L;
	private boolean treeType;
	private String parentCode;
	private String numbers;
	private double initTemp;
	private double finalTemp;
	private int tempIter;
	private long timeForComputing;
	private long timeForOutputCurrentRes;
	
	public InputData() {
		
	}
	
	@OneToMany(mappedBy = "inputData", orphanRemoval = true)
	private List<OutputData> outputData = new ArrayList<OutputData>();

	/**
	 * @return the treeType
	 */
	public boolean isTreeType() {
		return treeType;
	}

	/**
	 * @param treeType the treeType to set
	 */
	public void setTreeType(boolean treeType) {
		this.treeType = treeType;
	}

	/**
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 * @param parentCode the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * @return the numbers
	 */
	public String getNumbers() {
		return numbers;
	}

	/**
	 * @param numbers the numbers to set
	 */
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	/**
	 * @return the initTemp
	 */
	public double getInitTemp() {
		return initTemp;
	}

	/**
	 * @param initTemp the initTemp to set
	 */
	public void setInitTemp(double initTemp) {
		this.initTemp = initTemp;
	}

	/**
	 * @return the finalTemp
	 */
	public double getFinalTemp() {
		return finalTemp;
	}

	/**
	 * @param finalTemp the finalTemp to set
	 */
	public void setFinalTemp(double finalTemp) {
		this.finalTemp = finalTemp;
	}

	/**
	 * @return the tempIter
	 */
	public int getTempIter() {
		return tempIter;
	}

	/**
	 * @param tempIter the tempIter to set
	 */
	public void setTempIter(int tempIter) {
		this.tempIter = tempIter;
	}

	/**
	 * @return the timeForComputing
	 */
	public long getTimeForComputing() {
		return timeForComputing;
	}

	/**
	 * @param timeForComputing the timeForComputing to set
	 */
	public void setTimeForComputing(long timeForComputing) {
		this.timeForComputing = timeForComputing;
	}

	/**
	 * @return the timeForOutputCurrentRes
	 */
	public long getTimeForOutputCurrentRes() {
		return timeForOutputCurrentRes;
	}

	/**
	 * @param timeForOutputCurrentRes the timeForOutputCurrentRes to set
	 */
	public void setTimeForOutputCurrentRes(long timeForOutputCurrentRes) {
		this.timeForOutputCurrentRes = timeForOutputCurrentRes;
	}

}

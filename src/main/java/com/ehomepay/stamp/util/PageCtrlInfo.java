package com.ehomepay.stamp.util;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class PageCtrlInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7389249236880904779L;

    //Comment for <code>ALL_RECORD</code>
    public static final int ALL_RECORD = -100;

	//Comment for <code>pageAmount</code>
	private int pageAmount;

	// Comment for <code>rowsAmount</code>
	private int rowsAmount;
	
	//Comment for <code>pageIndex</code>
    private int pageIndex;
    
    //Comment for <code>pageSize</code>
    private int pageSize;
	/**
	 * @param pageIndex
	 * @param pageSize
	 */
	public PageCtrlInfo(int pageIndex, int pageSize){
		if(pageIndex < 1){
		    this.pageIndex = 1;
		}else{
		    this.pageIndex = pageIndex;
		}
	    this.pageSize = pageSize;
	}
	
	/**
	 * 计算总页数
	 */
	private void countPageAmount() { 
		if (this.rowsAmount % this.pageSize == 0) {
			this.pageAmount = this.rowsAmount / this.pageSize;
		} else {
			this.pageAmount = this.rowsAmount / this.pageSize + 1;
		}
		
		if( pageIndex > pageAmount){
            this.pageIndex = pageAmount;
        }
		
		if( this.pageIndex < 1 && this.pageIndex != ALL_RECORD){
			this.pageIndex = 1;
		}
	}
	
	/**
	 * @return
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	
	/**
	 * @return
	 */
	public int getPageAmount() {
		return pageAmount;
	}
	
	/**
	 * @return
	 */
	public int getRowsAmount() {
		return rowsAmount;
	}
	
	/**
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}
	
    /**
     * @param pageIndex
     */
    public void setPageIndex(int pageIndex) {
        if(pageIndex > pageAmount){
            this.pageIndex = pageAmount;
        } else if(pageIndex < 1 && pageIndex != ALL_RECORD){
            this.pageIndex = 1;
        } else {
            this.pageIndex = pageIndex;
        }
    }
    
    /**
     * @param rowsAmount
     */
    public void setRowsAmount(int rowsAmount) {
        this.rowsAmount = rowsAmount;
		this.countPageAmount();   
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
    

    /**
     * @param pageSize
     */
    public void setRowsPerPage(int pageSize) {
        this.pageSize = pageSize;
    }

   
}


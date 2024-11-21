package com.cris.recon.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

import com.couchbase.client.java.query.QueryScanConsistency;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(queryScanConsistency = QueryScanConsistency.REQUEST_PLUS )
@Collection(value = "CPG_UPI_RECONCILIATION_DETAIL")
@Scope(value = "RECONCILIATION")
@Data 
@Getter
@Setter
@ToString
public class CPGUPIReconDetail {
	
	@Field("id")
	@Id
	private String CPGUPIReconDetail_id;
	
	@Field("TICKET_TXN_ID") 
	private String tICKET_TXN_ID;
	
		@Field("APPLICATION") 
		private String aPPLICATION;
	    @Field("BANK_ACCOUNT") 
	    private long bANK_ACCOUNT;
	    @Field("BANK_ID") 
	    private int bANK_ID;
	    @Field("BANK_REFUND_ID") 
	    private String bANK_REFUND_ID;
	    @Field("BANK_REF_NUMBER") 
	    private String bANK_REF_NUMBER;
	    @Field("BOOKING_AMOUNT") 
	    private int bOOKING_AMOUNT;
	    @Field("CAN_TICKET_TXN_ID") 
	    private String cAN_TICKET_TXN_ID;
	    @Field("CPG_REFUND_ID") 
	    private String cPG_REFUND_ID;
	    @Field("CPG_TXN_ID") 
	    private String cPG_TXN_ID;
	    @Field("DIVISION_CODE") 
	    private String dIVISION_CODE;
	    @Field("LOCATION_ID") 
	    private int lOCATION_ID;
	    @Field("ORG_TKT_TXN_TYPE_ID") 
	    private int oRG_TKT_TXN_TYPE_ID;
	    @Field("ORG_TXN_STATUS") 
	    private int oRG_TXN_STATUS;
	    @Field("ORG_TXN_TIME") 
	    private String oRG_TXN_TIME;
	    @Field("RDS_REF_ID") 
	    private String rDS_REF_ID;
	    @Field("RECONCILE_DATE") 
	    private String rECONCILE_DATE;
	    @Field("RECONCILE_FLAG") 
	    private int rECONCILE_FLAG;
	    @Field("RECONCILIATION_VERSION") 
	    private int rECONCILIATION_VERSION;
	    @Field("REFUND_AMOUNT") 
	    private int rEFUND_AMOUNT;
	    @Field("REFUND_DATE") 
	    private String rEFUND_DATE;
	    @Field("REFUND_INIT_BANK_ERROR_DESC") 
	    private String rEFUND_INIT_BANK_ERROR_DESC;
	    @Field("REFUND_INIT_CPG_ERROR_DESC") 
	    private String rEFUND_INIT_CPG_ERROR_DESC;
	    @Field("REFUND_INIT_DATE") 
	    private String rEFUND_INIT_DATE;
	    @Field("REFUND_REASON") 
	    private String rEFUND_REASON;
	    @Field("REFUND_TYPE") 
	    private String rEFUND_TYPE;
	    @Field("REFUND_VALIDATED_DATE") 
	    private String rEFUND_VALIDATED_DATE;
	    @Field("REFUND_VALIDATION_BANK_DESC") 
	    private String rEFUND_VALIDATION_BANK_DESC;
	    @Field("REFUND_VALIDATION_CPG_DESC") 
	    private String rEFUND_VALIDATION_CPG_DESC;
	    @Field("STATION_CODE") 
	    private String sTATION_CODE;
	    @Field("STOCK_NUMBER") 
	    private String sTOCK_NUMBER;
	    @Field("TERMINAL_CODE") 
	    private String tERMINAL_CODE;
	    @Field("TICKET_NUMBER") 
	    private String tICKET_NUMBER;
	    
	    @Field("TXN_STATUS") 
	    private int tXN_STATUS;
	    @Field("TXN_TABLE") 
	    private boolean tXN_TABLE;
	    @Field("TXN_TIME") 
	    private String tXN_TIME;
	    @Field("UTS_PROCESS_DATE") 
	    private String uTS_PROCESS_DATE;
	    @Field("UTS_TXN_DATE") 
	    private String uTS_TXN_DATE;
	    @Field("VPA") 
	    private String vPA;
	    @Field("ZONE") 
	    private String zONE;
	    public String gettICKET_TXN_ID() {
			return tICKET_TXN_ID;
		}
		public void settICKET_TXN_ID(String tICKET_TXN_ID) {
			this.tICKET_TXN_ID = tICKET_TXN_ID;
		}
		public String getCPGUPIReconDetail_id() {
			return CPGUPIReconDetail_id;
		}
		public void setCPGUPIReconDetail_id(String cPGUPIReconDetail_id) {
			CPGUPIReconDetail_id = cPGUPIReconDetail_id;
		}
		public String getaPPLICATION() {
			return aPPLICATION;
		}
		public void setaPPLICATION(String aPPLICATION) {
			this.aPPLICATION = aPPLICATION;
		}
		public long getbANK_ACCOUNT() {
			return bANK_ACCOUNT;
		}
		public void setbANK_ACCOUNT(long bANK_ACCOUNT) {
			this.bANK_ACCOUNT = bANK_ACCOUNT;
		}
		public int getbANK_ID() {
			return bANK_ID;
		}
		public void setbANK_ID(int bANK_ID) {
			this.bANK_ID = bANK_ID;
		}
		public String getbANK_REFUND_ID() {
			return bANK_REFUND_ID;
		}
		public void setbANK_REFUND_ID(String bANK_REFUND_ID) {
			this.bANK_REFUND_ID = bANK_REFUND_ID;
		}
		public String getbANK_REF_NUMBER() {
			return bANK_REF_NUMBER;
		}
		public void setbANK_REF_NUMBER(String bANK_REF_NUMBER) {
			this.bANK_REF_NUMBER = bANK_REF_NUMBER;
		}
		public int getbOOKING_AMOUNT() {
			return bOOKING_AMOUNT;
		}
		public void setbOOKING_AMOUNT(int bOOKING_AMOUNT) {
			this.bOOKING_AMOUNT = bOOKING_AMOUNT;
		}
		public String getcAN_TICKET_TXN_ID() {
			return cAN_TICKET_TXN_ID;
		}
		public void setcAN_TICKET_TXN_ID(String cAN_TICKET_TXN_ID) {
			this.cAN_TICKET_TXN_ID = cAN_TICKET_TXN_ID;
		}
		public String getcPG_REFUND_ID() {
			return cPG_REFUND_ID;
		}
		public void setcPG_REFUND_ID(String cPG_REFUND_ID) {
			this.cPG_REFUND_ID = cPG_REFUND_ID;
		}
		public String getcPG_TXN_ID() {
			return cPG_TXN_ID;
		}
		public void setcPG_TXN_ID(String cPG_TXN_ID) {
			this.cPG_TXN_ID = cPG_TXN_ID;
		}
		public String getdIVISION_CODE() {
			return dIVISION_CODE;
		}
		public void setdIVISION_CODE(String dIVISION_CODE) {
			this.dIVISION_CODE = dIVISION_CODE;
		}
		public int getlOCATION_ID() {
			return lOCATION_ID;
		}
		public void setlOCATION_ID(int lOCATION_ID) {
			this.lOCATION_ID = lOCATION_ID;
		}
		public int getoRG_TKT_TXN_TYPE_ID() {
			return oRG_TKT_TXN_TYPE_ID;
		}
		public void setoRG_TKT_TXN_TYPE_ID(int oRG_TKT_TXN_TYPE_ID) {
			this.oRG_TKT_TXN_TYPE_ID = oRG_TKT_TXN_TYPE_ID;
		}
		public int getoRG_TXN_STATUS() {
			return oRG_TXN_STATUS;
		}
		public void setoRG_TXN_STATUS(int oRG_TXN_STATUS) {
			this.oRG_TXN_STATUS = oRG_TXN_STATUS;
		}
		public String getoRG_TXN_TIME() {
			return oRG_TXN_TIME;
		}
		public void setoRG_TXN_TIME(String oRG_TXN_TIME) {
			this.oRG_TXN_TIME = oRG_TXN_TIME;
		}
		public String getrDS_REF_ID() {
			return rDS_REF_ID;
		}
		public void setrDS_REF_ID(String rDS_REF_ID) {
			this.rDS_REF_ID = rDS_REF_ID;
		}
		public String getrECONCILE_DATE() {
			return rECONCILE_DATE;
		}
		public void setrECONCILE_DATE(String rECONCILE_DATE) {
			this.rECONCILE_DATE = rECONCILE_DATE;
		}
		public int getrECONCILE_FLAG() {
			return rECONCILE_FLAG;
		}
		public void setrECONCILE_FLAG(int rECONCILE_FLAG) {
			this.rECONCILE_FLAG = rECONCILE_FLAG;
		}
		public int getrECONCILIATION_VERSION() {
			return rECONCILIATION_VERSION;
		}
		public void setrECONCILIATION_VERSION(int rECONCILIATION_VERSION) {
			this.rECONCILIATION_VERSION = rECONCILIATION_VERSION;
		}
		public int getrEFUND_AMOUNT() {
			return rEFUND_AMOUNT;
		}
		public void setrEFUND_AMOUNT(int rEFUND_AMOUNT) {
			this.rEFUND_AMOUNT = rEFUND_AMOUNT;
		}
		public String getrEFUND_DATE() {
			return rEFUND_DATE;
		}
		public void setrEFUND_DATE(String rEFUND_DATE) {
			this.rEFUND_DATE = rEFUND_DATE;
		}
		public String getrEFUND_INIT_BANK_ERROR_DESC() {
			return rEFUND_INIT_BANK_ERROR_DESC;
		}
		public void setrEFUND_INIT_BANK_ERROR_DESC(String rEFUND_INIT_BANK_ERROR_DESC) {
			this.rEFUND_INIT_BANK_ERROR_DESC = rEFUND_INIT_BANK_ERROR_DESC;
		}
		public String getrEFUND_INIT_CPG_ERROR_DESC() {
			return rEFUND_INIT_CPG_ERROR_DESC;
		}
		public void setrEFUND_INIT_CPG_ERROR_DESC(String rEFUND_INIT_CPG_ERROR_DESC) {
			this.rEFUND_INIT_CPG_ERROR_DESC = rEFUND_INIT_CPG_ERROR_DESC;
		}
		public String getrEFUND_INIT_DATE() {
			return rEFUND_INIT_DATE;
		}
		public void setrEFUND_INIT_DATE(String rEFUND_INIT_DATE) {
			this.rEFUND_INIT_DATE = rEFUND_INIT_DATE;
		}
		public String getrEFUND_REASON() {
			return rEFUND_REASON;
		}
		public void setrEFUND_REASON(String rEFUND_REASON) {
			this.rEFUND_REASON = rEFUND_REASON;
		}
		public String getrEFUND_TYPE() {
			return rEFUND_TYPE;
		}
		public void setrEFUND_TYPE(String rEFUND_TYPE) {
			this.rEFUND_TYPE = rEFUND_TYPE;
		}
		public String getrEFUND_VALIDATED_DATE() {
			return rEFUND_VALIDATED_DATE;
		}
		public void setrEFUND_VALIDATED_DATE(String rEFUND_VALIDATED_DATE) {
			this.rEFUND_VALIDATED_DATE = rEFUND_VALIDATED_DATE;
		}
		public String getrEFUND_VALIDATION_BANK_DESC() {
			return rEFUND_VALIDATION_BANK_DESC;
		}
		public void setrEFUND_VALIDATION_BANK_DESC(String rEFUND_VALIDATION_BANK_DESC) {
			this.rEFUND_VALIDATION_BANK_DESC = rEFUND_VALIDATION_BANK_DESC;
		}
		public String getrEFUND_VALIDATION_CPG_DESC() {
			return rEFUND_VALIDATION_CPG_DESC;
		}
		public void setrEFUND_VALIDATION_CPG_DESC(String rEFUND_VALIDATION_CPG_DESC) {
			this.rEFUND_VALIDATION_CPG_DESC = rEFUND_VALIDATION_CPG_DESC;
		}
		public String getsTATION_CODE() {
			return sTATION_CODE;
		}
		public void setsTATION_CODE(String sTATION_CODE) {
			this.sTATION_CODE = sTATION_CODE;
		}
		public String getsTOCK_NUMBER() {
			return sTOCK_NUMBER;
		}
		public void setsTOCK_NUMBER(String sTOCK_NUMBER) {
			this.sTOCK_NUMBER = sTOCK_NUMBER;
		}
		public String gettERMINAL_CODE() {
			return tERMINAL_CODE;
		}
		public void settERMINAL_CODE(String tERMINAL_CODE) {
			this.tERMINAL_CODE = tERMINAL_CODE;
		}
		public String gettICKET_NUMBER() {
			return tICKET_NUMBER;
		}
		public void settICKET_NUMBER(String tICKET_NUMBER) {
			this.tICKET_NUMBER = tICKET_NUMBER;
		}
		public int gettXN_STATUS() {
			return tXN_STATUS;
		}
		public void settXN_STATUS(int tXN_STATUS) {
			this.tXN_STATUS = tXN_STATUS;
		}
		public boolean istXN_TABLE() {
			return tXN_TABLE;
		}
		public void settXN_TABLE(boolean tXN_TABLE) {
			this.tXN_TABLE = tXN_TABLE;
		}
		public String gettXN_TIME() {
			return tXN_TIME;
		}
		public void settXN_TIME(String tXN_TIME) {
			this.tXN_TIME = tXN_TIME;
		}
		public String getuTS_PROCESS_DATE() {
			return uTS_PROCESS_DATE;
		}
		public void setuTS_PROCESS_DATE(String uTS_PROCESS_DATE) {
			this.uTS_PROCESS_DATE = uTS_PROCESS_DATE;
		}
		public String getuTS_TXN_DATE() {
			return uTS_TXN_DATE;
		}
		public void setuTS_TXN_DATE(String uTS_TXN_DATE) {
			this.uTS_TXN_DATE = uTS_TXN_DATE;
		}
		public String getvPA() {
			return vPA;
		}
		public void setvPA(String vPA) {
			this.vPA = vPA;
		}
		public String getzONE() {
			return zONE;
		}
		public void setzONE(String zONE) {
			this.zONE = zONE;
		}
}

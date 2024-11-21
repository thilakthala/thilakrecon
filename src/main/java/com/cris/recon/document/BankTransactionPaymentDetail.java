package com.cris.recon.document;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

import com.couchbase.client.core.deps.com.google.type.Date;
import com.couchbase.client.core.deps.com.google.type.DateTime;
import com.couchbase.client.java.query.QueryScanConsistency;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Document(queryScanConsistency=QueryScanConsistency.REQUEST_PLUS)
@TypeAlias("")

@Collection(value="bank_txn_payment_detail")
@Scope(value="RECONCILIATION")
@Data
@Getter
@Setter
@ToString
public class BankTransactionPaymentDetail {
	@Field("txn_ref_id")
	@Id
	private String txnRefId ;
	@Field("app_code")
	private String appCode ;
	@Field("cpg_appl_code")
	private String cpgApplCode;
	@Field("tkt_uts_no")
	private String tktUtsNo ;
	@Field("tkt_stock_no")
	private String tktStockNo ;
	@Field("tkt_amt")
	private Double tktAmt;
	@Field("tkt_trml_id")
	private String tktTrmlId ;
	@Field("tkt_trml_sessId")
	private Integer tktTrmlSessId ;
	@Field("loc_id")
	private Integer locId ;
	@Field("stn_id")
	private Integer stnId ;
	@Field("div_code")
	private String divCode;
	@Field("zone_code")
	private String  zoneCode;
	@Field("tkt_type")
	private String tktType ;
	@Field("tkt_sub_type")
	private String  tktSubType ;
	@Field("pymt_mode_id")
	private Integer pymtModeId ;
	@Field("pymt_method_id")
	private Integer pymtMethodId ;
	@Field("cpg_pymt_mode_id")
	private Integer cpgPymtModeId ;
	@Field("cpg_ban_id")
	private Integer cpgBanId;
	@Field("vpa")
	private String  vpa;
	@Field("card_type")
	private String cardType;
	@Field("card_no")
	private String  cardNo; 
	@Field("pos_mid")
	private String posMid ;
	@Field("pos_tid")
	private String posTid;
	@Field("bank_code")
	private String bankCode;
	@Field("rrn")
	private  String rrn ;
	@Field("invoice_no")
	private String invoiceNo ;
	@Field("card_srvc_provider")
	private String  cardSrvcProvider ;
	@Field("txn_status")
	private Integer txnStatus ;
	@Field("pymt_init_date")
	private Date pymtInitDate ;
	@Field("pymt_init_time")
	private DateTime  pymtInitTime ;
	@Field("cpg_pymt_txn_id")
	private String cpgPymtTxnId ;
	@Field("cpg_response_time")
	private DateTime cpgResponseTime;
	@Field("cpg_pymt_status")
	private Integer cpgPymtStatus;
	@Field("cpg_msg")
	private String  cpgMsg;
	@Field("pymt_confirm_time")
	private DateTime pymtConfirmTime ;
	@Field("bank_ref_no")
	private String bankRefNo;
	public String getTxnRefId() {
		return txnRefId;
	}
	public void setTxnRefId(String txnRefId) {
		this.txnRefId = txnRefId;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getCpgApplCode() {
		return cpgApplCode;
	}
	public void setCpgApplCode(String cpgApplCode) {
		this.cpgApplCode = cpgApplCode;
	}
	public String getTktUtsNo() {
		return tktUtsNo;
	}
	public void setTktUtsNo(String tktUtsNo) {
		this.tktUtsNo = tktUtsNo;
	}
	public String getTktStockNo() {
		return tktStockNo;
	}
	public void setTktStockNo(String tktStockNo) {
		this.tktStockNo = tktStockNo;
	}
	public Double getTktAmt() {
		return tktAmt;
	}
	public void setTktAmt(Double tktAmt) {
		this.tktAmt = tktAmt;
	}
	public String getTktTrmlId() {
		return tktTrmlId;
	}
	public void setTktTrmlId(String tktTrmlId) {
		this.tktTrmlId = tktTrmlId;
	}
	public Integer getTktTrmlSessId() {
		return tktTrmlSessId;
	}
	public void setTktTrmlSessId(Integer tktTrmlSessId) {
		this.tktTrmlSessId = tktTrmlSessId;
	}
	public Integer getLocId() {
		return locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
	}
	public Integer getStnId() {
		return stnId;
	}
	public void setStnId(Integer stnId) {
		this.stnId = stnId;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getZoneCode() {
		return zoneCode;
	}
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	public String getTktType() {
		return tktType;
	}
	public void setTktType(String tktType) {
		this.tktType = tktType;
	}
	public String getTktSubType() {
		return tktSubType;
	}
	public void setTktSubType(String tktSubType) {
		this.tktSubType = tktSubType;
	}
	public Integer getPymtModeId() {
		return pymtModeId;
	}
	public void setPymtModeId(Integer pymtModeId) {
		this.pymtModeId = pymtModeId;
	}
	public Integer getPymtMethodId() {
		return pymtMethodId;
	}
	public void setPymtMethodId(Integer pymtMethodId) {
		this.pymtMethodId = pymtMethodId;
	}
	public Integer getCpgPymtModeId() {
		return cpgPymtModeId;
	}
	public void setCpgPymtModeId(Integer cpgPymtModeId) {
		this.cpgPymtModeId = cpgPymtModeId;
	}
	public Integer getCpgBanId() {
		return cpgBanId;
	}
	public void setCpgBanId(Integer cpgBanId) {
		this.cpgBanId = cpgBanId;
	}
	public String getVpa() {
		return vpa;
	}
	public void setVpa(String vpa) {
		this.vpa = vpa;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getPosMid() {
		return posMid;
	}
	public void setPosMid(String posMid) {
		this.posMid = posMid;
	}
	public String getPosTid() {
		return posTid;
	}
	public void setPosTid(String posTid) {
		this.posTid = posTid;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getRrn() {
		return rrn;
	}
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getCardSrvcProvider() {
		return cardSrvcProvider;
	}
	public void setCardSrvcProvider(String cardSrvcProvider) {
		this.cardSrvcProvider = cardSrvcProvider;
	}
	public Integer getTxnStatus() {
		return txnStatus;
	}
	public void setTxnStatus(Integer txnStatus) {
		this.txnStatus = txnStatus;
	}
	public Date getPymtInitDate() {
		return pymtInitDate;
	}
	public void setPymtInitDate(Date pymtInitDate) {
		this.pymtInitDate = pymtInitDate;
	}
	public DateTime getPymtInitTime() {
		return pymtInitTime;
	}
	public void setPymtInitTime(DateTime pymtInitTime) {
		this.pymtInitTime = pymtInitTime;
	}
	public String getCpgPymtTxnId() {
		return cpgPymtTxnId;
	}
	public void setCpgPymtTxnId(String cpgPymtTxnId) {
		this.cpgPymtTxnId = cpgPymtTxnId;
	}
	public DateTime getCpgResponseTime() {
		return cpgResponseTime;
	}
	public void setCpgResponseTime(DateTime cpgResponseTime) {
		this.cpgResponseTime = cpgResponseTime;
	}
	public Integer getCpgPymtStatus() {
		return cpgPymtStatus;
	}
	public void setCpgPymtStatus(Integer cpgPymtStatus) {
		this.cpgPymtStatus = cpgPymtStatus;
	}
	public String getCpgMsg() {
		return cpgMsg;
	}
	public void setCpgMsg(String cpgMsg) {
		this.cpgMsg = cpgMsg;
	}
	public DateTime getPymtConfirmTime() {
		return pymtConfirmTime;
	}
	public void setPymtConfirmTime(DateTime pymtConfirmTime) {
		this.pymtConfirmTime = pymtConfirmTime;
	}
	public String getBankRefNo() {
		return bankRefNo;
	}
	public void setBankRefNo(String bankRefNo) {
		this.bankRefNo = bankRefNo;
	}
	public String getBatchSettleId() {
		return batchSettleId;
	}
	public void setBatchSettleId(String batchSettleId) {
		this.batchSettleId = batchSettleId;
	}
	public String getRdsTxnRefId() {
		return rdsTxnRefId;
	}
	public void setRdsTxnRefId(String rdsTxnRefId) {
		this.rdsTxnRefId = rdsTxnRefId;
	}
	public Integer getReconFlag() {
		return reconFlag;
	}
	public void setReconFlag(Integer reconFlag) {
		this.reconFlag = reconFlag;
	}
	public String getReconSettleId() {
		return reconSettleId;
	}
	public void setReconSettleId(String reconSettleId) {
		this.reconSettleId = reconSettleId;
	}
	public DateTime getReconTime() {
		return reconTime;
	}
	public void setReconTime(DateTime reconTime) {
		this.reconTime = reconTime;
	}
	public String getQrCodeString() {
		return qrCodeString;
	}
	public void setQrCodeString(String qrCodeString) {
		this.qrCodeString = qrCodeString;
	}
	public DateTime getLastModified() {
		return lastModified;
	}
	public void setLastModified(DateTime lastModified) {
		this.lastModified = lastModified;
	}
	@Field("batch_settle_id")
	private String batchSettleId ;
	@Field("rds_txn_ref_id")
	private String rdsTxnRefId ;
	@Field("recon_flag")
	private Integer reconFlag ;
	@Field("recon_settle_id")
	private String reconSettleId ;
	@Field("recon_time")
	private DateTime reconTime;
	@Field("qr_code_string")
	private String  qrCodeString ;
	@Field("last_modified")
	private DateTime lastModified ; 
	String typeKey() {
		return "";
	}
}

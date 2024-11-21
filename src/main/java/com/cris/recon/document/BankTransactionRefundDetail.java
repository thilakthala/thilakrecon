package com.cris.recon.document;

import com.couchbase.client.java.query.QueryScanConsistency;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Document(queryScanConsistency= QueryScanConsistency.REQUEST_PLUS)
@TypeAlias("")

@Collection(value="bank_txn_refund_detail")
@Scope(value="RECONCILIATION")
@Data
@Getter
@Setter
@ToString
public class BankTransactionRefundDetail {
    @Field("txn_ref_id")
    @Id
    private String txnRefId ;
    @Field("app_code")
    private String appcode;
    @Field("cpg_appl_code")
    private String cpgApplCode;
    @Field(("tkt_uts_no"))
    private String tktUtsNo;
    @Field("tkt_stock_no")
    private String tktStockNo;
    @Field("tkt_amt")
    private BigDecimal tktAmt;
    @Field(("refund_amt"))
    private BigDecimal refundAmt;
    @Field("link_txn_ref_id")
    private String linkTxnRefId;
    @Field("tkt_trml_id")
    private String tktTrmlId;
    @Field(("tkt_trml_sess_id"))
    private int tktTrmlSessId;
    @Field("loc_id")
    private int locId;
    @Field("div_code")
    private String divCode;
    @Field("zone_code")
    private String zoneCode;
    @Field(("tkt_type"))
    private String tktType;
    @Field("tkt_sub_type")
    private String tktSubType;
    @Field("txn_type")
    private String txnType;
    @Field("pymt_mode_id")
    private int pymtModeId;
    @Field("pymt_method_id")
    private int pymtMethodId;
    @Field(("cpg_pymt_mode_id"))
    private int cpgPymtModeId;
    @Field("cpg_bank_id")
    private int cpgBankId;
    @Field("cpg_pymt_txn_id")
    private String cpgPymtTxnId;
    @Field("rds_pymt_txn_ref_id")
    private String rdsPymtTxnRefId;
    @Field("vpa")
    private String vpa;
    @Field(("card_no"))
    private String cardNo;
    @Field("pos_mid")
    private String posMid;
    @Field("pos_tid")
    private String posTid;
    @Field("bank_code")
    private String bankCode;
    @Field("rrn")
    private String rrn;
    @Field(("invoice_no"))
    private String invoiceNo;
    @Field("card_srvc_provider")
    private String cardSrvcProvider;
    @Field("txn_status")
    private int txnStatus;
    @Field("refund_init_date")
    private Date refundInitDate;
    @Field("refund_init_time")
    private Time refundInitTime;
    @Field(("cpg_refund_txn_id"))
    private String cpgRefundTxnId;
    @Field("cpg_response_time")
    private Timestamp cpgResponseTime;
    @Field("cpg_refund_status")
    private int cpgRefundStatus;
    @Field(("cpg_msg"))
    private String cpgMsg;
    @Field("refund_confirm_time")
    private Timestamp refundConfirmTime;
    @Field("bank_ref_no")
    private String bankRefNo;
    @Field(("batch_settle_id"))
    private String batchSettleId;
    @Field("rds_refund_txn_ref_id")
    private String rdsRefundTxnRefId;
    @Field("recon_flag")
    private int reconFlag;
    @Field(("recon_settle_id"))
    private String reconSettleId;
    @Field("recon_time")
    private Timestamp reconTime;
    @Field("last_modified_date")
    private Timestamp lastModified_date;

    public String getTxnRefId() {
        return txnRefId;
    }

    public void setTxnRefId(String txnRefId) {
        this.txnRefId = txnRefId;
    }

    public String getAppcode() {
        return appcode;
    }

    public void setAppcode(String appcode) {
        this.appcode = appcode;
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

    public BigDecimal getTktAmt() {
        return tktAmt;
    }

    public void setTktAmt(BigDecimal tktAmt) {
        this.tktAmt = tktAmt;
    }

    public BigDecimal getRefundAmt() {
        return refundAmt;
    }

    public void setRefundAmt(BigDecimal refundAmt) {
        this.refundAmt = refundAmt;
    }

    public String getLinkTxnRefId() {
        return linkTxnRefId;
    }

    public void setLinkTxnRefId(String linkTxnRefId) {
        this.linkTxnRefId = linkTxnRefId;
    }

    public String getTktTrmlId() {
        return tktTrmlId;
    }

    public void setTktTrmlId(String tktTrmlId) {
        this.tktTrmlId = tktTrmlId;
    }

    public int getTktTrmlSessId() {
        return tktTrmlSessId;
    }

    public void setTktTrmlSessId(int tktTrmlSessId) {
        this.tktTrmlSessId = tktTrmlSessId;
    }

    public int getLocId() {
        return locId;
    }

    public void setLocId(int locId) {
        this.locId = locId;
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

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public int getPymtModeId() {
        return pymtModeId;
    }

    public void setPymtModeId(int pymtModeId) {
        this.pymtModeId = pymtModeId;
    }

    public int getPymtMethodId() {
        return pymtMethodId;
    }

    public void setPymtMethodId(int pymtMethodId) {
        this.pymtMethodId = pymtMethodId;
    }

    public int getCpgPymtModeId() {
        return cpgPymtModeId;
    }

    public void setCpgPymtModeId(int cpgPymtModeId) {
        this.cpgPymtModeId = cpgPymtModeId;
    }

    public int getCpgBankId() {
        return cpgBankId;
    }

    public void setCpgBankId(int cpgBankId) {
        this.cpgBankId = cpgBankId;
    }

    public String getCpgPymtTxnId() {
        return cpgPymtTxnId;
    }

    public void setCpgPymtTxnId(String cpgPymtTxnId) {
        this.cpgPymtTxnId = cpgPymtTxnId;
    }

    public String getRdsPymtTxnRefId() {
        return rdsPymtTxnRefId;
    }

    public void setRdsPymtTxnRefId(String rdsPymtTxnRefId) {
        this.rdsPymtTxnRefId = rdsPymtTxnRefId;
    }

    public String getVpa() {
        return vpa;
    }

    public void setVpa(String vpa) {
        this.vpa = vpa;
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

    public int getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(int txnStatus) {
        this.txnStatus = txnStatus;
    }

    public Date getRefundInitDate() {
        return refundInitDate;
    }

    public void setRefundInitDate(Date refundInitDate) {
        this.refundInitDate = refundInitDate;
    }

    public Time getRefundInitTime() {
        return refundInitTime;
    }

    public void setRefundInitTime(Time refundInitTime) {
        this.refundInitTime = refundInitTime;
    }

    public String getCpgRefundTxnId() {
        return cpgRefundTxnId;
    }

    public void setCpgRefundTxnId(String cpgRefundTxnId) {
        this.cpgRefundTxnId = cpgRefundTxnId;
    }

    public Timestamp getCpgResponseTime() {
        return cpgResponseTime;
    }

    public void setCpgResponseTime(Timestamp cpgResponseTime) {
        this.cpgResponseTime = cpgResponseTime;
    }

    public int getCpgRefundStatus() {
        return cpgRefundStatus;
    }

    public void setCpgRefundStatus(int cpgRefundStatus) {
        this.cpgRefundStatus = cpgRefundStatus;
    }

    public String getCpgMsg() {
        return cpgMsg;
    }

    public void setCpgMsg(String cpgMsg) {
        this.cpgMsg = cpgMsg;
    }

    public Timestamp getRefundConfirmTime() {
        return refundConfirmTime;
    }

    public void setRefundConfirmTime(Timestamp refundConfirmTime) {
        this.refundConfirmTime = refundConfirmTime;
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

    public String getRdsRefundTxnRefId() {
        return rdsRefundTxnRefId;
    }

    public void setRdsRefundTxnRefId(String rdsRefundTxnRefId) {
        this.rdsRefundTxnRefId = rdsRefundTxnRefId;
    }

    public int getReconFlag() {
        return reconFlag;
    }

    public void setReconFlag(int reconFlag) {
        this.reconFlag = reconFlag;
    }

    public String getReconSettleId() {
        return reconSettleId;
    }

    public void setReconSettleId(String reconSettleId) {
        this.reconSettleId = reconSettleId;
    }

    public Timestamp getReconTime() {
        return reconTime;
    }

    public void setReconTime(Timestamp reconTime) {
        this.reconTime = reconTime;
    }

    public Timestamp getLastModified_date() {
        return lastModified_date;
    }

    public void setLastModified_date(Timestamp lastModified_date) {
        this.lastModified_date = lastModified_date;
    }
}

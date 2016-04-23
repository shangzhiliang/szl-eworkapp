package com.org.decp.msg.model.base;

import javax.validation.Valid;
import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("DataExchangePackage")
public class DataExchangePackage
{

  @XStreamAlias("EnvelopInfo")
  @Valid
  EnvelopInfo envelopInfo;

  @XStreamAlias("TransferInfo")
  @Valid
  TransferInfo transferInfo;

  @XStreamAlias("ContentControl")
  @Valid
  ContentControl contentControl;

  @XStreamAlias("PackageInfo")
  @Valid
  PackageInfo packageInfo;

  @XStreamAlias("ReturnState")
  @Valid
  ReturnState returnState;

  public DataExchangePackage()
  {
    Init();
  }

  protected void Init()
  {
    EnvelopInfo envelopInfo = new EnvelopInfo();
    setEnvelopInfo(envelopInfo);
    TransferInfo transferInfo = new TransferInfo();
    setTransferInfo(transferInfo);
    ContentControl contentControl = new ContentControl();
    setContentControl(contentControl);
    PackageInfo packageInfo = new PackageInfo();
    setPackageInfo(packageInfo);

    ReturnState returnState = new ReturnState();

    setReturnState(returnState);
  }

  public EnvelopInfo getEnvelopInfo() {
    return this.envelopInfo;
  }

  public void setEnvelopInfo(EnvelopInfo envelopInfo) {
    this.envelopInfo = envelopInfo;
  }

  public TransferInfo getTransferInfo() {
    return this.transferInfo;
  }

  public void setTransferInfo(TransferInfo transferInfo) {
    this.transferInfo = transferInfo;
  }

  public ContentControl getContentControl() {
    return this.contentControl;
  }

  public void setContentControl(ContentControl contentControl) {
    this.contentControl = contentControl;
  }

  public PackageInfo getPackageInfo() {
    return this.packageInfo;
  }

  public void setPackageInfo(PackageInfo packageInfo) {
    this.packageInfo = packageInfo;
  }

  public ReturnState getReturnState() {
    return this.returnState;
  }

  public void setReturnState(ReturnState returnState) {
    this.returnState = returnState;
  }
}
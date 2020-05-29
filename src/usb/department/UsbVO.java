package usb.department;

public class UsbVO {
	private String group_date;
	private String amount;
	private String classify;
	
	public UsbVO()
	{
		
	}
	
	public UsbVO(String igroup_date,String iamount, String iclassify)
	{
		group_date = igroup_date;
		amount = iamount;
		classify = iclassify;
	}

	public String getGroup_date() {
		return group_date;
	}

	public void setGroup_date(String group_date) {
		this.group_date = group_date;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("usbVO [group_date=");
		builder.append(group_date);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", classify=");
		builder.append(classify);
		builder.append("]");
		return builder.toString();
	}

}

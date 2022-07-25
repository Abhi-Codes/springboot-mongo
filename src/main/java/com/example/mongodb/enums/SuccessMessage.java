package com.example.mongodb.enums;

import lombok.Getter;

@Getter
public enum SuccessMessage {

	// Application messages
	APPLICATION_CREATED("Application created successfully", "TAKE1-A-001"),
	APPLICATION_CREATED_SUBMITTED("Application created & submitted successfully", "TAKE1-A-002"),
	APPLICATION_SAVED("Application saved successfully", "TAKE1-A-003"),
	APPLICATION_SUBMITTED("Application submitted successfully", "TAKE1-A-004"),
	APPLICATION_SENT_BACK("Application sent back successfully", "TAKE1-A-005"),
	APPLICATION_APPROVED("Application approved successfully", "TAKE1-A-006"),
	APPLICATION_REJECTED("Application rejected successfully", "TAKE1-A-007"),
	APPLICATION_WITHDRAWN("Application withdrawn successfully", "TAKE1-A-008"),
	APPLICATION_WITHDRAWN_PENDING(
			"Application withdrawal will be reviewed by the Take 1 team before processing request", "TAKE1-A-009"),
	APPLICATION_WITHDRAWN_APPROVED("Application withdraw approved successfully", "TAKE1-A-010"),
	APPLICATION_COMPLETED("Application completed successfully", "TAKE1-A-011"),
	APPLICATION_DISCARDED("Application completed successfully", "TAKE1-A-012"),
	APPLICATION_RESUBMITTED("Application re-submitted successfully", "TAKE1-A-013"),
	APPLICATION_REIMBURSE_AMOUNT_UPDATED("Application reimbursement amount updated successfully", "TAKE1-A-014"),
	APPLICATION_COMPLETION_DOC_UPLOADED("Completion document uploaded successfully", "TAKE1-A-015"),
	APPLICATION_DELETED("Application deleted successfully", "TAKE1-A-016"),
	APPLICATION_WITHDRAWN_CANCELLED("Application withdraw cancelled successfully", "TAKE1-A-017"),

	// Application Approval messages
	APPLICATION_APPROVAL_REVOKED("Application approval revoked successfully", "TAKE1-AA-001"),

	// Application comment messages
	APPLICATION_COMMENT_ADDED("Application comments added successfully", "TAKE1-AC-001"),

	// Application File messages
	APPLICATION_FILE_UPLOADED("File uploded to the server successfully", "TAKE1-AF-001"),
	APPLICATION_FILE_UPLOADED_TEMP_LOC("File uploaded to a temporary location successfully", "TAKE1-AF-002"),
	APPLICATION_FILE_DELETED("Attachment deleted successfully", "TAKE1-AF-003"),

	// Application payment messages
	APPLICATION_PAYMENT_ALL_UPDATED("All application expense reimbursements have been updated successfully",
			"TAKE1-AP-001"),

	// Application report messages
	APPLICATION_REPORT_LISTING_CSV("Application listing report will be sent to your mail shortly", "TAKE1-AR-001"),

	// Opportunity messages
	OPPORTUNITY_CREATED("Opportunity created successfully", "TAKE1-O-001"),
	OPPORTUNITY_UPDATED("Opportunity updated successfully", "TAKE1-O-002"),
	OPPORTUNITY_ACTION_UPDATED("Opportunity updated successfully", "TAKE1-O-003"),
	OPPORTUNITY_DELETED("Opportunity deleted successfully", "TAKE1-O-004"),

	// Opportunity messages
	OPPORTUNITY_TYPE_CREATED("Opportunity type created successfully", "TAKE1-OT-001"),
	OPPORTUNITY_TYPE_UPDATED("Opportunity type updated successfully", "TAKE1-OT-002"),
	OPPORTUNITY_TYPE_DELETED("Opportunity type deleted successfully", "TAKE1-OT-003"),

	// Opportunity type workflow messages
	OPPORTUNITY_TYPE_WORKFLOW_CREATED("Workflow created successfully", "TAKE1-OTW-001"),
	OPPORTUNITY_TYPE_WORKFLOW_UPDATED("Workflow updated successfully", "TAKE1-OTW-002"),
	OPPORTUNITY_TYPE_WORKFLOW_DELETED("Workflow deleted successfully", "TAKE1-OTW-004"),
	OPPORTUNITY_TYPE_WORKFLOW_ALL_DELETED("All Opportunity type workflows are deleted successfully", "TAKE1-OTW-004"),

	// Opportunity type workflow messages
	OPPORTUNITY_TYPE_WORKFLOW_CUST_CREATED("Opportunity type custom workflow created successfully", "TAKE1-OTWC-001"),
	OPPORTUNITY_TYPE_WORKFLOW_CUST_UPDATED("Opportunity type custom workflow updated successfully", "TAKE1-OTWC-002"),
	OPPORTUNITY_TYPE_WORKFLOW_CUST_DELETED("Opportunity type custom workflow deleted successfully", "TAKE1-OTWC-004"),

	// User messages
	USER_EMAIL_SETTING_UPDATED("User email setting updated successfully", "TAKE1-U-001"),

	// Vapprove messages
	VAPPROVE_APPLICATION_APPROVED("Application approved successfully", "TAKE1-VA-001"),
	VAPPROVE_APPLICATION_REJECTED("Application rejected successfully", "TAKE1-VA-002"),

	// Feedback messages
	FEEDBACK_SUBMITTED("Congratualtions !! You have successfully submitted feedback", "TAKE1-F-001"),
	FEEDBACK_DELETED("Feedback has been deleted successfully", "TAKE1-F-002"),
	FEEDBACK_REPORT_LISTING_CSV("Feedback listing report will be sent to your mail shortly", "TAKE1-F-003"),

	// Opportunity List
	OPPORTUNITY_REPORT_LISTING_CSV("Opportunity listing report will be sent to your mail shortly", "TAKE1-OR-001"),

	// Other messages
	EMPTY("", "");

	SuccessMessage(String msg, String code) {
		this.msg = msg;
		this.code = code;
	}

	private String msg;
	private String code;
}

package com.example.mongodb.enums;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorMessage {

	// Application
	APPLICATION_NOT_FOUND_WITH_ID("The application you are trying to view/modify cannot be found - {0}",
			HttpStatus.NOT_FOUND.value()),
	APPLICATION_APPLIED_ALREADY(
			"You have already applied to this opportunity, a duplicate application cannot be received",
			HttpStatus.BAD_REQUEST.value()),
	APPLICATION_CANNOT_DELETE("Only applications in draft status can be deleted", HttpStatus.UNAUTHORIZED.value()),
	APPLICATION_CANNOT_SENT_BACK("This application cannot be sent back, as the current status does not allow it",
			HttpStatus.UNAUTHORIZED.value()),
	APPLICATION_CANNOT_APPROVE("This application cannot be approved, as the current status does not allow it",
			HttpStatus.UNAUTHORIZED.value()),
	APPLICATION_CANNOT_WITHDRAW("This application cannot be withdrawn, as the current status does not allow it",
			HttpStatus.UNAUTHORIZED.value()),
	APPLICATION_CANNOT_COMPLETE(
			"This application cannot be marked as complete, as the current status does not allow it",
			HttpStatus.UNAUTHORIZED.value()),
	APPLICATION_CANNOT_DISCARD("This application cannot be discarded, as the current status does not allow it",
			HttpStatus.UNAUTHORIZED.value()),
	APPLICATION_CANNOT_RESUBMIT("This application cannot be resubmitted, as the current status does not allow it",
			HttpStatus.UNAUTHORIZED.value()),
	APPLICATION_CANNOT_UPDATE_REIMBURSE_AMT(
			"You cannot update the expense reimbursed amount on this application, as the current status does not allow it",
			HttpStatus.UNAUTHORIZED.value()),
	APPLICATION_CANNOT_UPLOAD_COMPLETION_DOC(
			"You cannot upload completion documents to this application, as the current status does not allow it",
			HttpStatus.UNAUTHORIZED.value()),
	APPLICATION_CANNOT_UPDATE_ADMIN_NOTES("You do not have sufficient permission to update admin notes",
			HttpStatus.UNAUTHORIZED.value()),

	APPLICATION_OTHER_USER_APPROVE_REJECT_APPLICATION(
			"This application is currently pending action from another user, you are not authorized for action at this stage",
			HttpStatus.UNAUTHORIZED.value()),
	APPLICATION_WITHDRAW_CANNOT_APPROVE(
			"This application cannot be approved for withdrawal, as the current status does not allow it",
			HttpStatus.UNAUTHORIZED.value()),
	APPLICATION_COST_EXCEEDED_USER_BUDGET("The application cost exceeds your allocated budget for this year",
			HttpStatus.INTERNAL_SERVER_ERROR.value()),
	APPLICATION_COST_EXCEEDED_CATEGORY_BUDGET(
			"The application cost exceeds the limit allowed for this opportunity type",
			HttpStatus.INTERNAL_SERVER_ERROR.value()),

	// Application Approval
	APPLICATION_APPROVAL_NOT_FOUND("No application approval found with id - {0}", HttpStatus.NOT_FOUND.value()),
	APPLICATION_APPROVAL_CANNOT_REVOKE(
			"The approvals can only be revoked if the application status is in PENDING_APPROVAL or APPROVED",
			HttpStatus.UNAUTHORIZED.value()),
	APPLICATION_APPROVAL_HAS_NEXT_APPROVED(
			"This approval cannot be revoked as the next approval for this application is already approved, please revoke that first",
			HttpStatus.UNAUTHORIZED.value()),

	// Application comment type
	APPLICATION_COMMENT_TYPE_NOT_FOUND_WITH_ID("No application comment type found with id - {0}",
			HttpStatus.NOT_FOUND.value()),

	// Application files
	APPLICATION_FILE_NOT_FOUND_WITH_ID("No attachments have been found with ID - {0}", HttpStatus.NOT_FOUND.value()),
	APPLICATION_FILE_NOT_FOUND("No attachments found", HttpStatus.NOT_FOUND.value()),
	APPLICATION_FILE_UNSUPPORTED_FORMAT(
			"This file format is unsupported.  Accepted formats are JPG, JPEG, PNG, GIF, BMP & PDF",
			HttpStatus.BAD_REQUEST.value()),
	APPLICATION_FILE_PATH_EMPTY("File path cannot be empty", HttpStatus.BAD_REQUEST.value()),
	APPLICATION_FILE_UNSUPPORTED_CHARACTER(
			"Something is wrong! Ensure filename does not have ‘%' or ‘..' (two consecutive dots) and try again.",
			HttpStatus.BAD_REQUEST.value()),
	APPLICATION_TEMP_FILE_INVALID_PATH("Temporary file path provided in the request is not valid",
			HttpStatus.BAD_REQUEST.value()),

	// Application status
	APPLICATION_STATUS_NOT_FOUND_WITH_ID("No application status found with Id - {0}", HttpStatus.NOT_FOUND.value()),

	// Application withdraw type
	APPLICATION_WITHDRAW_TYPE_NOT_FOUND_WITH_ID("No application withdraw type found with Id - {0}",
			HttpStatus.NOT_FOUND.value()),

	// Opportunity
	OPPORTUNITY_NOT_FOUND_WITH_ID("No opportunity found with Id - {0}", HttpStatus.NOT_FOUND.value()),
	OPPORTUNITY_COST_IN_LOCAL_CURRENCY_REQUIRED("Cost in Local Currency is required when Local Currency is selected",
			HttpStatus.BAD_REQUEST.value()),
	OPPORTUNITY_NOT_ACTIVE_OR_PUBLISHED("This opportunity is not available", HttpStatus.INTERNAL_SERVER_ERROR.value()),
	OPPORTUNITY_CANNOT_SENT_BACK("This opportunity cannot be sent back, the current status does not allow it",
			HttpStatus.UNAUTHORIZED.value()),
	OPPORTUNITY_CANNOT_ARCHIVED("This opportunity cannot be archived, the current status does not allow it",
			HttpStatus.UNAUTHORIZED.value()),

	// To be refactored
	OPPORTUNITY_MANDATORY_FIELDS_MISSING("Mandatory fields are missing please check - {0}",
			HttpStatus.BAD_REQUEST.value()),
	OPPORTUNITY_MANDATORY_START_END_DATE(
			"Please confirm that mandatory fields 'start date' or 'end date' are not left blank - {0}, {1}",
			HttpStatus.BAD_REQUEST.value()),

	// Opportunity type
	OPPORTUNITY_TYPE_NOT_FOUND_WITH_ID("No opportunity type found with Id - {0}", HttpStatus.NOT_FOUND.value()),
	OPPORTUNITY_TYPE_CANNOT_DELETE(
			"There are opportunities associated to this opportunity type, please delete them first",
			HttpStatus.INTERNAL_SERVER_ERROR.value()),
	OPPORTUNITY_TYPE_MAXBUDGET_EXCEEDED(
			"Maximum limit for any Opportunity_Type cannot exceed the annual Employee_Budget limit.",
			HttpStatus.BAD_REQUEST.value()),
	OPPORTUNITY_TYPE_ALREADY_EXISTS("Opportunity Type already exists", HttpStatus.BAD_REQUEST.value()),
	OPPORTUNITY_TYPE_CANNOT_UPDATE(
			"Opportunity Type cannot be changed as there are applications linked to this opportunity that will impact the budget limit for the Opportunity Types",
			HttpStatus.BAD_REQUEST.value()),

	// Opportunity type workflow
	OPPORTUNITY_TYPE_WORKFLOW_NOT_FOUND_WITH_ID("No opportunity type workflow found with Id - {0}",
			HttpStatus.NOT_FOUND.value()),

	// Opportunity type workflow custom
	OPPORTUNITY_TYPE_WORKFLOW_CUSTOM_NOT_FOUND_WITH_ID("No opportunity type workflow custom found with Id - {0}",
			HttpStatus.NOT_FOUND.value()),

	// Opportunity comment type
	OPPORTUNITY_COMMENT_TYPE_NOT_FOUND_WITH_ID("No opportunity comment type found with id - {0}",
			HttpStatus.NOT_FOUND.value()),

	// Opportunity status
	OPPORTUNITY_STATUS_NOT_FOUND_WITH_ID("No opportunity status found with Id - {0}", HttpStatus.NOT_FOUND.value()),

	// Opportunity Vendor
	OPPORTUNITY_VENDOR_NOT_FOUND_WITH_ID("No opportunity vendor found with Id - {0}", HttpStatus.NOT_FOUND.value()),

	// User error messages
	USER_NOT_FOUND_WITH_ID("No user record found for the userId - {0}", HttpStatus.NOT_FOUND.value()),
	USER_NOT_FOUND_WITH_USERNAME("No user record found for the username - {0}", HttpStatus.NOT_FOUND.value()),
	USER_NO_REPORTEES_FOUND("The user {0} doesn't have any reportees", HttpStatus.INTERNAL_SERVER_ERROR.value()),
	USER_NOT_FOUND_IN_HEADER("User not found in header", HttpStatus.INTERNAL_SERVER_ERROR.value()),

	// Settings
	SETTINGS_NOT_FOUND("No settings record found with the name - {0}", HttpStatus.NOT_FOUND.value()),
	SETTINGS_ID_NOT_FOUND("No settings record found with id - {0}", HttpStatus.NOT_FOUND.value()),
	SETTINGS_TAKE1_PROG_FREEZED("The Take 1 site is currently closed and not accepting any applications",
			HttpStatus.INTERNAL_SERVER_ERROR.value()),
	SETTINGS_USER_EXP_NOT_ELIGIBLE(
			"Oops! You are not eligible to apply to the Take 1 program, applicants must have at least {0} months of continuous service with VMware.",
			HttpStatus.INTERNAL_SERVER_ERROR.value()),
	SETTINGS_USER_NLE_NOT_ELIGIBLE(
			"Oops! You are not eligible to apply to the Take 1 program, applicants based out of a Dell Legal Entity cannot apply to Take 1.",
			HttpStatus.INTERNAL_SERVER_ERROR.value()),
	SETTINGS_CANNOT_SUBMIT_APPLICATION_PAST_DUE(
			"Oops! You cannot submit this application as a previous application is past the end date and is awaiting completion documents.",
			HttpStatus.INTERNAL_SERVER_ERROR.value()),

	// Comment
	COMMENT_INVALID_TYPE("Comment type provided is invalid, please provide a valid type",
			HttpStatus.BAD_REQUEST.value()),
	COMMENT_EMPTY("Comment is mandatory for this action", HttpStatus.BAD_REQUEST.value()),

	// Others
	ACTION_INVALID("The value provided for the action is invalid or empty, please provide a valid action",
			HttpStatus.BAD_REQUEST.value()),
	NO_ACCESS(
			"You do not have sufficient permissions on this. Please contact the administrator if you feel you got this message in error.",
			HttpStatus.UNAUTHORIZED.value()),
	FILTER_FORMAT_INVALID("Filter criteria provided is not in the desired format - {0}",
			HttpStatus.BAD_REQUEST.value()),
	FILTER_UNSUPPORTED_BOOLEAN_OPERATION("Unsupported filter operator for boolean type - {0}",
			HttpStatus.BAD_REQUEST.value()),
	FILTER_INVALID_INPUT_FOR_APPLICATION_APPROVAL("{0} is not a valid option for applicationApprovals filter key",
			HttpStatus.BAD_REQUEST.value()),
	INTERNAL_SERVER_EXCEPTION("Something went wrong, please try again after sometime",
			HttpStatus.INTERNAL_SERVER_ERROR.value()),

	// User Role Messages
	USER_IMPERSONATE_SUPERADMIN("Superadmins cannot be impersonated", HttpStatus.INTERNAL_SERVER_ERROR.value()),
	USER_IMPERSONATE_DENY("User cannot be impersonated", HttpStatus.INTERNAL_SERVER_ERROR.value()),
	USER_IMPERSONATION_RECORDS_NOT_FOUND("No impersonation records found for user - {0}", HttpStatus.NOT_FOUND.value()),
	USER_ROLE_NOT_FOUND("No Roles found for user - {0}", HttpStatus.NOT_FOUND.value()),

	// Quarterly Budget Settings
	BUDGET_VALIDATE_PAST_QUARTER("You cannot modify data from past quarters", HttpStatus.INTERNAL_SERVER_ERROR.value()),
	BUDGET_VALIDATE_APPROVED(
			"The quarterly budget limit and soft close limit cannot be less than the current total approved application budget",
			HttpStatus.INTERNAL_SERVER_ERROR.value()),
	BUDGET_VALIDATE_SOFT_CLOSE("Soft close limit cannot be greater than quarterly budget",
			HttpStatus.INTERNAL_SERVER_ERROR.value()),
	BUDGET_VALIDATE_OPEN_DATE("The application open date should not be less than current quarter start date",
			HttpStatus.INTERNAL_SERVER_ERROR.value()),
	BUDGET_DUPLICATE_RECORD("Budget settings for this quarter already exist", HttpStatus.INTERNAL_SERVER_ERROR.value()),
	BUDGET_NOT_FOUND("Budget setting for this quarter is not found for id - {0}", HttpStatus.NOT_FOUND.value()),

	// Opportunity Status
	OPPORTUNITY_STATUS_NOT_FOUND("Opportunity Status not found with id - {0}", HttpStatus.NOT_FOUND.value()),

	// Page
	PAGE_NOT_FOUND_WITH_ID("No page record found for id - {0}", HttpStatus.NOT_FOUND.value()),
	PAGE_INVALID_URL("The page URL provided is not a valid format, please provide a vaild one - {0}",
			HttpStatus.BAD_REQUEST.value()),

	// Report
	REPORT_APPLICATION_REQUEST_EMPTY_STATUS("Status should not be empty, please provide the status as query parameter",
			HttpStatus.BAD_REQUEST.value()),
	REPORT_APPLICATION_REQUEST_EMPTY_DATE_RANGE("Date range is not provided, please provide the date range",
			HttpStatus.BAD_REQUEST.value()),

	// LDAP
	LDAP_ERROR("Error in LDAP connection", HttpStatus.INTERNAL_SERVER_ERROR.value()),

	// Workday
	WORKDAY_ERROR("Error in Workday connection", HttpStatus.INTERNAL_SERVER_ERROR.value()),

	// CPM
	CPM_TOKEN_ERROR("Error in fetching CPM token", HttpStatus.INTERNAL_SERVER_ERROR.value()),

	// Coveo
	COVEO_TOKEN_ERROR("Error in fetching Coveo token", HttpStatus.INTERNAL_SERVER_ERROR.value()),

	// JWT
	JWT_MISSING_VERIFIERKEYS("Missing Verifier Keys", HttpStatus.INTERNAL_SERVER_ERROR.value()),
	JWT_TOKEN_DECODE_ERROR("Failed to decode JWT token", HttpStatus.INTERNAL_SERVER_ERROR.value()),

	// Fiscal Year
	FISCAL_YEAR_NOT_FOUND("Fiscal Year not found", HttpStatus.NOT_FOUND.value()),
	FISCAL_YEAR_QUARTER_NOT_FOUND("Fiscal Year and Quarter not found", HttpStatus.NOT_FOUND.value()),

	// Concur Email
	EMAIL_FOLDER_NOT_FOUND("Folder not found - {0}", HttpStatus.NOT_FOUND.value()),

	// VApprove
	DATE_RANGE_INVALID("End DateTime cannot be less than Start DateTime", HttpStatus.BAD_REQUEST.value()),

	INVALID_REQUEST("Invalid Request", HttpStatus.BAD_REQUEST.value()),
	INVALID_RATINGS("Please provide ratings from 1-5", HttpStatus.BAD_REQUEST.value()),
	DUPLICATE_RATINGS("Thank you for your feedback", HttpStatus.INTERNAL_SERVER_ERROR.value()),
	FEEDBACK_NOT_FOUND("Oops! Feedback not found", HttpStatus.NOT_FOUND.value());

	ErrorMessage(String msg, int errCode) {
		this.msg = msg;
		this.errCode = errCode;
	}

	private String msg;
	private int errCode;
}

package com._98point6.droptoken.service.exception;

public enum ErrorMessages {
    ILLEGAL_MOVE("Illegal Move "),
    INVALID_INPUT("Invalid Input "),
    MALFORMED_REQUEST("Not a valid Request sent. Please look into body request "),
    NO_RECORD_FOUND("No record found for provided id "),
    RECORD_ALREADY_EXISTS("Record already exists "),
    INTERNAL_SERVER_ERROR("Something went wrong. Please repeat this operation later. ");
    
   private String errorMessage;
   
   ErrorMessages(String errorMessage)
   {
      this.errorMessage = errorMessage;    
   }
    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }
    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
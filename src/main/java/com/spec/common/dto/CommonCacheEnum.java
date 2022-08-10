package com.spec.common.dto;

public enum CommonCacheEnum {

    CustomerDetailById(Constants.CustomerDetailById);

    CommonCacheEnum(String CacheName){}

    public static class Constants {
    	
        private Constants() {
		}

		public static final String CustomerDetailById = "CustomerDetailById";
    }

}

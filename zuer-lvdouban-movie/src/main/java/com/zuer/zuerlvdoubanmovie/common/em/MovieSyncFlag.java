package com.zuer.zuerlvdoubanmovie.common.em;

public enum MovieSyncFlag {

        SYNC_0("未同步","0"),
        SYNC_1("同步开始","1"),
        SYNC_2("同步完成","2"),;

        private String type;
        private String value;

        MovieSyncFlag(String type,String value){
            this.type = type;
            this.value = value;
        }
        public String getValue(){
            return value;
        }
        public String getType(){
            return type;
        }

}

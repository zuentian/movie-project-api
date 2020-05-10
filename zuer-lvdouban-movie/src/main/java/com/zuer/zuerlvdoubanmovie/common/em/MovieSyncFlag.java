package com.zuer.zuerlvdoubanmovie.common.em;

public enum MovieSyncFlag {

        SYNC_0("0","未同步"),
        SYNC_1("1","同步开始"),
        SYNC_2("2","同步完成"),;

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

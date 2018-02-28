package com.kb260.gxdk.entity;

import java.util.List;

/**
 * @author KB260
 *         Created on  2017/12/5
 */

public class PlateDetailNew {

    private List<PinpaiListBean> pinpai_list;

    public List<PinpaiListBean> getPinpai_list() {
        return pinpai_list;
    }

    public void setPinpai_list(List<PinpaiListBean> pinpai_list) {
        this.pinpai_list = pinpai_list;
    }

    public static class PinpaiListBean {
        /**
         * ppname : 一汽大众(奥迪)
         * xilie : [{"xlname":"奥迪100","xlid":"20000001"},{"xlname":"奥迪200","xlid":"20000002"},{"xlname":"奥迪A4","xlid":"20000053"},{"xlname":"奥迪A4L","xlid":"20000054"},{"xlname":"奥迪A6","xlid":"20000055"},{"xlname":"奥迪A6L","xlid":"20000056"},{"xlname":"奥迪Q3","xlid":"20000155"},{"xlname":"奥迪Q5","xlid":"20000156"},{"xlname":"奥迪A3","xlid":"20000528"}]
         * ppid : 3000047
         */

        private String ppname;
        private String ppid;
        private List<XilieBean> xilie;

        public String getPpname() {
            return ppname;
        }

        public void setPpname(String ppname) {
            this.ppname = ppname;
        }

        public String getPpid() {
            return ppid;
        }

        public void setPpid(String ppid) {
            this.ppid = ppid;
        }

        public List<XilieBean> getXilie() {
            return xilie;
        }

        public void setXilie(List<XilieBean> xilie) {
            this.xilie = xilie;
        }

        public static class XilieBean {
            /**
             * xlname : 奥迪100
             * xlid : 20000001
             */

            private String xlname;
            private String xlid;

            public String getXlname() {
                return xlname;
            }

            public void setXlname(String xlname) {
                this.xlname = xlname;
            }

            public String getXlid() {
                return xlid;
            }

            public void setXlid(String xlid) {
                this.xlid = xlid;
            }
        }
    }
}

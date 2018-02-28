package com.kb260.gxdk.entity;

import java.util.List;

/**
 * @author KB260
 *         Created on  2017/12/5
 */

public class PlateDetailThree {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * chexing_list : [{"price":"36.00","id":"1020273","cxname":"奥迪100 1992款 2.6 手动 E 化油器","pyear":"1991"},{"price":"32.50","id":"1020268","cxname":"奥迪100 1992款 2.2 手动 E 化油器","pyear":"1991"},{"price":"33.50","id":"1020263","cxname":"奥迪100 1992款 2.2 自动 E 化油器","pyear":"1991"},{"price":"32.00","id":"1020257","cxname":"奥迪100 1992款 2.0 手动 化油器","pyear":"1991"},{"price":"30.00","id":"1020252","cxname":"奥迪100 1992款 1.8 手动 化油器","pyear":"1991"}]
         * pyear : 1992
         */

        private int pyear;
        private List<ChexingListBean> chexing_list;

        public int getPyear() {
            return pyear;
        }

        public void setPyear(int pyear) {
            this.pyear = pyear;
        }

        public List<ChexingListBean> getChexing_list() {
            return chexing_list;
        }

        public void setChexing_list(List<ChexingListBean> chexing_list) {
            this.chexing_list = chexing_list;
        }

        public static class ChexingListBean {
            /**
             * price : 36.00
             * id : 1020273
             * cxname : 奥迪100 1992款 2.6 手动 E 化油器
             * pyear : 1991
             */

            private String price;
            private String id;
            private String cxname;
            private String pyear;

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCxname() {
                return cxname;
            }

            public void setCxname(String cxname) {
                this.cxname = cxname;
            }

            public String getPyear() {
                return pyear;
            }

            public void setPyear(String pyear) {
                this.pyear = pyear;
            }
        }
    }
}

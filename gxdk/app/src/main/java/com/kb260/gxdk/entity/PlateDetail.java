package com.kb260.gxdk.entity;

import java.util.List;

/**
 * @author KB260
 *         Created on  2017/11/17
 */

public class PlateDetail {

    /**
     * id : 284
     * name : 奥克斯
     * fullname :
     * initial : A
     * price :
     * parentid : 6
     * depth : 2
     * carlist : [{"id":"285","name":"朗杰","fullname":"朗杰","initial":"A","parentid":"284","logo":"http://pic1.jisuapi.cn/car/static/images/logo/300/285.png","price":"","salestate":"停销","depth":"3","list":[{"id":"3541","name":"2004款 豪华型","initial":"A","parentid":"285","logo":"","price":"7.88万","yeartype":"2004","productionstate":"停产","salestate":"停产","sizetype":"SUV","depth":"4"},{"id":"3542","name":"2004款 普通型","initial":"A","parentid":"285","logo":"","price":"暂无","yeartype":"2004","productionstate":"停产","salestate":"停产","sizetype":"SUV","depth":"4"}]},{"id":"286","name":"原动力","fullname":"原动力","initial":"A","parentid":"284","logo":"http://pic1.jisuapi.cn/car/static/images/logo/300/286.png","price":"","salestate":"停销","depth":"3","list":[{"id":"3543","name":"2003款 舒适级","initial":"A","parentid":"286","logo":"","price":"暂无","yeartype":"2003","productionstate":"停产","salestate":"停产","sizetype":"SUV","depth":"4"},{"id":"3544","name":"2003款 精英级","initial":"A","parentid":"286","logo":"","price":"暂无","yeartype":"2003","productionstate":"停产","salestate":"停产","sizetype":"SUV","depth":"4"},{"id":"3545","name":"2002款 尊享型","initial":"A","parentid":"286","logo":"","price":"暂无","yeartype":"2002","productionstate":"停产","salestate":"停产","sizetype":"SUV","depth":"4"}]}]
     */

    private String id;
    private String name;
    private String fullname;
    private String initial;
    private String price;
    private String parentid;
    private String depth;
    private List<CarlistBean> carlist;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public List<CarlistBean> getCarlist() {
        return carlist;
    }

    public void setCarlist(List<CarlistBean> carlist) {
        this.carlist = carlist;
    }

    public static class CarlistBean {
        /**
         * id : 285
         * name : 朗杰
         * fullname : 朗杰
         * initial : A
         * parentid : 284
         * logo : http://pic1.jisuapi.cn/car/static/images/logo/300/285.png
         * price :
         * salestate : 停销
         * depth : 3
         * list : [{"id":"3541","name":"2004款 豪华型","initial":"A","parentid":"285","logo":"","price":"7.88万","yeartype":"2004","productionstate":"停产","salestate":"停产","sizetype":"SUV","depth":"4"},{"id":"3542","name":"2004款 普通型","initial":"A","parentid":"285","logo":"","price":"暂无","yeartype":"2004","productionstate":"停产","salestate":"停产","sizetype":"SUV","depth":"4"}]
         */

        private String id;
        private String name;
        private String fullname;
        private String initial;
        private String parentid;
        private String logo;
        private String price;
        private String salestate;
        private String depth;
        private List<ListBean> list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getInitial() {
            return initial;
        }

        public void setInitial(String initial) {
            this.initial = initial;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSalestate() {
            return salestate;
        }

        public void setSalestate(String salestate) {
            this.salestate = salestate;
        }

        public String getDepth() {
            return depth;
        }

        public void setDepth(String depth) {
            this.depth = depth;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 3541
             * name : 2004款 豪华型
             * initial : A
             * parentid : 285
             * logo :
             * price : 7.88万
             * yeartype : 2004
             * productionstate : 停产
             * salestate : 停产
             * sizetype : SUV
             * depth : 4
             */

            private String id;
            private String name;
            private String initial;
            private String parentid;
            private String logo;
            private String price;
            private String yeartype;
            private String productionstate;
            private String salestate;
            private String sizetype;
            private String depth;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getInitial() {
                return initial;
            }

            public void setInitial(String initial) {
                this.initial = initial;
            }

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getYeartype() {
                return yeartype;
            }

            public void setYeartype(String yeartype) {
                this.yeartype = yeartype;
            }

            public String getProductionstate() {
                return productionstate;
            }

            public void setProductionstate(String productionstate) {
                this.productionstate = productionstate;
            }

            public String getSalestate() {
                return salestate;
            }

            public void setSalestate(String salestate) {
                this.salestate = salestate;
            }

            public String getSizetype() {
                return sizetype;
            }

            public void setSizetype(String sizetype) {
                this.sizetype = sizetype;
            }

            public String getDepth() {
                return depth;
            }

            public void setDepth(String depth) {
                this.depth = depth;
            }
        }
    }
}

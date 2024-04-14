const API = {
    hostName: "",
    cookiePath: "",
    API_MERCHANDISE_CATEGORY_PATH: "/api/v1/admin/merchandise/category",
    getMerchandiseCategoryUrl: function (id) {
        return id ? (this.API_MERCHANDISE_CATEGORY_PATH + "/" + id) : this.API_MERCHANDISE_CATEGORY_PATH;
    },
    getMerchandiseCategoryAttributesUrl: function () {
        return this.API_MERCHANDISE_CATEGORY_PATH + "/attributes";

    },

    API_MERCHANDISE_BRAND_PATH: "/api/v1/admin/merchandise/brand",
    getMerchandiseBrandUrl: function (id) {
        return id ? (this.API_MERCHANDISE_BRAND_PATH + "/" + id) : this.API_MERCHANDISE_BRAND_PATH;
    },
    API_MERCHANDISE_PRODUCT_PATH: "/api/v1/admin/merchandise/product",
    getMerchandiseProductUrl: function (id) {
        return id ? (this.API_MERCHANDISE_PRODUCT_PATH + "/" + id) : this.API_MERCHANDISE_PRODUCT_PATH;
    },
    API_MERCHANDISE_REVIEW_PATH: "/api/v1/admin/merchandise/review",
    getMerchandiseReviewUrl: function (id) {
        return id ? (this.API_MERCHANDISE_REVIEW_PATH + "/" + id) : this.API_MERCHANDISE_REVIEW_PATH;
    },
    API_SALES_PROMOTION_PROMOTION_PATH: "/api/v1/admin/sales-promotion/promotion",
    getSalesPromotionPromotionUrl: function (id) {
        return id ? (this.API_SALES_PROMOTION_PROMOTION_PATH + "/" + id) : this.API_SALES_PROMOTION_PROMOTION_PATH;
    },
    API_SALES_PROMOTION_FLASH_SALE_PATH: "/api/v1/admin/sales-promotion/flash-sale",
    getSalesPromotionFlashSaleUrl: function (id) {
        return id ? (this.API_SALES_PROMOTION_FLASH_SALE_PATH + "/" + id) : this.API_SALES_PROMOTION_FLASH_SALE_PATH;
    },
    API_SALES_PRODUCT_FLASH_SALE_PATH: "/api/v1/admin/sales-promotion/product-flash-sale",
    getSalesProductFlashSaleUrl: function (id) {
        return id ? (this.API_SALES_PRODUCT_FLASH_SALE_PATH + "/" + id) : this.API_SALES_PRODUCT_FLASH_SALE_PATH;
    },
    API_REQUEST_PATH: "/api/v1/admin/request",
    getRequestUrl: function (id) {
        return id ? (this.API_REQUEST_PATH + "/" + id) : this.API_REQUEST_PATH;
    }
    // ,
    // API_NOTIFICATION_PATH: "/api/v1/admin/notification",
    // getNotificationUrl: function (id) {
    //     return id ? (this.API_NOTIFICATION_PATH + "/" + id) : this.API_NOTIFICATION_PATH;
    // }
};

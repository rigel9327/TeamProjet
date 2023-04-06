package com.example.team_project.domain.domain.coupons.service.delete.couponincategory;

import com.example.team_project.domain.domain.coupons.domain.Coupon;

public interface CouponInCategoryDeleteService {
    /**
     * Copon을 삭제하는 경우
     * 쿠폰과 연관되어 있는 카테고리 맵핑 DB를 모두
     * 삭제한다.
     *
     * @param coupon 삭제할 쿠폰
     */
    void deleteByCouponKindsId(Coupon coupon);
}

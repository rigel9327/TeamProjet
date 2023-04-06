package com.example.team_project.domain.domain.coupons.service.apply;

import com.example.team_project.domain.domain.coupons.domain.*;
import com.example.team_project.domain.domain.order.item.domain.Order;
import com.example.team_project.domain.domain.product.category.domain.ProductCategory;
import com.example.team_project.exception.NotApplyCouponException;
import com.example.team_project.exception.NotFoundCouponException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CouponApplyServiceImpl implements CouponApplyService {
    private final UserHaveCouponRepository userHaveCouponRepository;
    private final CouponInCategoryRepository couponInCategoryRepository;

    @Override
    public int apply(Long userId, Long couponId, Order order) {
        Coupon coupon = getCoupon(userId, couponId).getCoupon();

        if (!applyAble(coupon, order)) {
            throw new NotApplyCouponException();
        }

        return order.getOrderToProduct().getTotalPrice() * (100 - coupon.getDiscountRate()) / 100;
    }

    private UserHaveCoupon getCoupon(Long userId, Long couponId) {
        return userHaveCouponRepository.findByUserIdAndId(userId, couponId).orElseThrow(() ->
                new NotFoundCouponException(("User does not have the coupon. Coupon ID:" + couponId)));
    }

    private boolean applyAble(Coupon coupon, Order order) {
        return isApplicableToCategory(coupon.getName(), order) &&
                isMinimumPriceSatisfied(order, coupon.getMinPrice());
    }

    private boolean isApplicableToCategory(String couponName, Order order) {
        ProductCategory orderCategory = order.getOrderToProduct().getProduct().getCategory();

        return couponInCategoryRepository.existsByCouponNameAndProductCategory(couponName, orderCategory);
    }

    private boolean isMinimumPriceSatisfied(Order order, int couponMinPrice) {
        return order.getOrderToProduct().getTotalPrice() >= couponMinPrice;
    }
}
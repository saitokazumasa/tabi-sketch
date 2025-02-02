//package com.tabisketch.bean.form;
//
//import com.tabisketch.bean.entity.Plan;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
//public class CreatePlanForm {
//    @NotBlank
//    private String title;
//
//    @Email
//    @NotBlank
//    private String userMailAddress;
//
//    public Plan toPlan(final int userId) {
//        return Plan.generate(
//                this.title,
//                userId
//        );
//    }
//}

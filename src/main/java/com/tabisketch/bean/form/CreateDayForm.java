//package com.tabisketch.bean.form;
//
//import com.tabisketch.bean.entity.Day;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
//public class CreateDayForm {
//    @Min(1)
//    private int day;
//
//    @Min(1)
//    private int planId;
//
//    @Min(0)
//    private int walkThreshold;
//
//    @Size(min = 4, max = 4)
//    private String preferTransportationListBinary;
//
//    @NotNull
//    private boolean useTollRoad;
//
//    @NotNull
//    private boolean useFerry;
//
//    public Day toDay() {
//        return Day.generate(
//                this.day,
//                this.planId,
//                this.walkThreshold,
//                this.preferTransportationListBinary
//        );
//    }
//}

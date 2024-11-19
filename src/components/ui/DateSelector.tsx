     

import React from "react";
import { StyleSheet } from "react-native";
import DateTimePickerModal from "react-native-modal-datetime-picker";

import { useDateSelector } from "@/hooks/useDateSelector";

    export const DateSelector = () => {
        
            const {date, handleConfirm, hideDatePicker, showDatePicker, isDatePickerVisible} = useDateSelector()
    
      return (
        
           <DateTimePickerModal
             isVisible={isDatePickerVisible}
             mode="date"
             onConfirm={handleConfirm}
             onCancel={hideDatePicker}
           />
        
      );
    };
    
    export default DateSelector;

const styles = StyleSheet.create({
    fecha: {
        alignSelf: "center",
        marginBottom:8
    },
    dateSelector: {
        marginVertical: 8
    },
    buton: {
        borderRadius: 20
    }
})
import moment from "moment";
import { useState } from "react";

export const useDateSelector = () => {
        
    const [isDatePickerVisible, setDatePickerVisibility] = useState(false);

    const [date, setDate] = useState(new Date)
  
    const showDatePicker = () => {
      setDatePickerVisibility(true);
    };
  
    const hideDatePicker = () => {
      setDatePickerVisibility(false);
      
    };
  
    const handleConfirm = (date:Date) => {
      setDate(date);
      hideDatePicker();
    }

    return{ date, hideDatePicker, showDatePicker, handleConfirm, isDatePickerVisible}
}
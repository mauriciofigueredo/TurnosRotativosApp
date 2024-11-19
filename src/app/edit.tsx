import { View } from 'react-native'
import React from 'react'
import { useDrizzleStudio } from "expo-drizzle-studio-plugin";
import * as SQLite from "expo-sqlite";
import Ionicons from '@expo/vector-icons/Ionicons';

const edit = () => {
    const db = SQLite.useSQLiteContext();

    useDrizzleStudio(db);

    return <View>
          <Ionicons name="checkmark-circle" size={32} color="green" /> 
    </View>;

}

export default edit
import React from 'react'
import { Stack } from 'expo-router'
import { SQLiteProvider } from 'expo-sqlite'
import { initializeDatabase } from '@/database/initializeDatabase'
import * as eva from '@eva-design/eva';
import { ApplicationProvider, IconRegistry, Layout, Text } from '@ui-kitten/components';
import { useColorScheme } from 'react-native';

const layout = () => {
  const color = useColorScheme()

  const theme = color === 'dark'? eva.dark: eva.light
  return (
    <SQLiteProvider databaseName='redes.db' onInit={initializeDatabase}>
      
     
      <ApplicationProvider {...eva} theme={theme}>
    
        <Stack  screenOptions={{
          headerStyle: {
            backgroundColor: '#32217b',
          },
          headerTintColor: '#d6cef5',
          headerTitleAlign: 'center'
        }}>
            <Stack.Screen name='home' options={{title: 'Splash', animation:'ios'}}/>
            <Stack.Screen name='index' options={{title: 'Turnos Redes', animation:'ios'}}/>
            <Stack.Screen name='edit'options={{title: 'Ver Base de Datos', animation:'ios'}}/>
            <Stack.Screen name='update' options={{title: 'Actualizar fechas', animation: 'ios'}}/>


        </Stack>
      </ApplicationProvider>
      
    </SQLiteProvider>
  )
}

export default layout
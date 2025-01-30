# Practica 08 - Espera / Wait

### 1. Per què s’atura l’execució al cap d’un temps?

Quan un fil es troba que totes les places estan plenes, s'atura l'execució del fil i es queda esperant a que alguna de les places es buidi per poder continuar amb la seva execució. Per tant, quan totes les places s'acaben arriva un moment en que tots els fils estan esperant a que alguna de les places es buidi pero no hi ha cap fil que pugui buidar les places, per tant, tots els fils estan esperant indefinidament.

### 2. Què passaria si en lloc de una probabilitat de 50%-50% fora de 70%-30%? I si foren al revés les probabilitats?

Si la probabilitat de que un fil es quedi esperant es del 70% i la probabilitat de que un fil es quedi treballant es del 30%, això vol dir que la probabilitat de que un fil es quedi esperant es major que la probabilitat de que un fil es quedi treballant. Per tant, la probabilitat de que tots els fils es quedin esperant es major que la probabilitat de que tots els fils es quedin treballant. Per tant, la probabilitat de que tots els fils es quedin esperant es major que la probabilitat de que tots els fils es quedin treballant.

### 3. Per què creus que fa falta la llista i no valdria només amb una variable sencera de reserves?

Perque com els fils estan interactuant constantment amb les reserves, si només tinguessim una variable sencera de reserves, aquesta variable s'actualitzaria constantment i no sabriem quants fils estan treballant i quants estan esperant. Per tant, necessitem una llista per poder saber quants fils estan treballant i quants estan esperant.
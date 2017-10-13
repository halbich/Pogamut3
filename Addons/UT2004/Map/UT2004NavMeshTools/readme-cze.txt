Ahoj,

ok, tak kdy� bude� cht�t cokoliv vysv�tlit tak se ozvi. P��padn� kdybys cht�l doprogramovat n�jak� konkr�tn� m�n�� celek, tak ti s t�m m��u pomoct tak �e bych to d�lal s�m t�eba n�jak p�es v�kend, to bychom se v tom p��pad� je�t� domluvili.

Z�sk�n� navmeshe m� 3 ��sti:

1. dostat z mapy (.ut2) geometrii (.xml)
2. p�eform�tovat geometrii do form�tu pou�iteln�ho pro Recast (.obj)
3. z�skat navmesh z Recatsu (.navmesh)

Jak to prov�st:

1. Sta�� spustit mnou upraven� UShock na vybranou mapu. Je to command-line utilita, tak�e nap��klad nap�e� do cmd "UShock.exe DM-1-on-1-Albatross.ut2" a v�sledn� geometrie se ulo�� do adres��e output vedle bin�rky UShocku. Ten adres�� output tam mus� b�t p�edem, automaticky se nevytvo�� mysl�m. Pro hromadn� vyextrahov�n� v�ech map jsem napsal batch skript bulk_transform.bat, kter� sta�� spustit dvojklikem, a on p�evede v�echny mapy, kter� najde v adres��i map UT2004. Cesta k tomuto adres��i je napsan� uvn�t� skriptu a na m�m po��ta�i je to "C:\Program Files\GOG.com\Unreal Tournament 2004\maps". To si p��padn� mus�, p�epsat, pokud jsou mapy jinde.

UShock.exe i bulk_transform.bat jsou na CD v Attachments\03-AlteredUShock\win32_binary. O adres�� vedle jsou pak zdroj�ky UShocku, pokud bys ho cht�l upravovat.

2. P�edpokl�d�me, �e m� adres�� s hromadou .xml fil�. V tomhle kroku se form�t dat jenom p�evede z XML na n�co jako CSV s mezerami. Nav�c se geometrie vycentruje, aby m�la st�ed v [0;0;0] a zv�t�� / zmen��, aby jej� nejdel�� rozm�r sahal od -100 do +100. Tento p�evod prov�d� java t��da UShock2Recast, respektive jej� metoda main. Na vstupu bere 2 parametry: adres��, kde m� hledat geometrii z UShocku a n�zev souboru s geometr��, kter� m� zpracovat.
�ili v p��kazov� ��dce by to m�lo j�t spustit nap�. p��kazem:
java UShock2Recast D:\navMesh\UShock\output DM-1-on-1-Albatross.xml
Op�t je mo�n� p�ev�st v �echny mapy najednou a k tomuto ��elu existuje skript skript.bat, kter� t��du spust� na v�echny soubory, kter� najde v zadan�m adres��i, kter� je v n�m napsan�. V m�m p��pad� je to D:\navMesh\UShock\moje-binarka\output

Bouh�el z n�jak�ho d�vodu mi na m�m po��ta�i nefunguje spu�t�n� tohoto p��kazu z p��kazov� ��dky a proto kdy� chci tento krok prov�st, tak si projekt UT2004LevelGeom otev�u v Netbeansech, nastav�m dva zm�n�n�  vstupn� parametry v properties projektu a spust�m t��du  UShock2Recast tam, p��padn� MultiUShock2Recast pro p�evod v�ech map v po�adovan�m adres��i. MultiUShock2Recast bere jedin� parametr a to n�zev adres��e, ve kter�m m� hledat soubory. Na ka�d� z nich pa spust� oby�ejn� UShock2Recast.

Na v�stupu (op�t aders�� output) tedy dostane� .obj soubory a ke ka�d�mu je�t� .scale a .centre soubor, kde jsou informace o transformaci sou�adnic, kter� bude v n�sleduj�c�m kroku pot�eboat Recast.

Ko�en tohoto Netbeans projektu je v Attachments\05-
AlteredUT2004LevelGeom\UT2004LevelGeom.

3. P�edpokl�d�me, �e m� adres�� s hromadou .obj, . scale a .centre fil�.
P�evod na .navmesh provede Recast. Sta�� spustit Recast s jedin�m parametrem - n�zev mapy.obj. Recast si u� pak s�m vyhled� soubory s p��ponami .scale a .centre (mus� existovat). V�echny vstupy Recast hled� v adres��i Meshes (relativn� vedle bin�rky) a v�stupy ukl�d� do adres��e output (relativn� vedle bin�rky, mus� existovat).

Pro p��klad exsituj� na CD skripty run.bat (spust� p�eklad pro mapu DM-Flux)
a runAll.bat (spust� p�ekald pro v�echny mapy)

V�echny tyto soubory najde� v Attachments\07-AlteredRecast\recastnavigation-read-only\RecastDemo\Bin.
Zdroj�ky jsou jako v�dy o adres�� vedle. Recast je o dost pomalej�� ne� UShock a p�elo�it v�echny mapy mi trvalo n�kolik hodin.
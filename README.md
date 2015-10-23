# SignIns
This is a program to use with a Magnetic Card reader to read Cal Poly ID Cards.
First you enter the amount of points to add
Then you can either swipe a Poly Card, or Type a name.
If you swipe a Poly Card, it will check the ISO number against the list in FILENAME
If it is there, it will update the points.
If it is not there, it will ask the user for a name.
If the name is in the list, it will update the ISO number.
If the name is not in the list, it will add the name and ISO number to the table.

Isntead of swiping a Poly Card, you can type a name instead.
If it is in the list, it will update the points.
If it is not in the list, then it will create a fake number as an ISO and add it to the list. 

It will then update the points. 

To stop checking input, enter "quit" or "q". 
It will then print the list. 

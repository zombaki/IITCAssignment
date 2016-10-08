#include "types.h"
#include "stat.h"
#include "user.h"

int
main(void)
{
	//DECLARAE SECTION
	int intInitialTick,intTotalTicks,intSysCalls;
	//SYSTEM FUNCTION CALL BEFORE OUR EVENT
	intInitialTick=getAllTicks(0);//intInitialTick will have value before invoking our method.
	getUserTraps(0); //intialize before event
	
	//********** invoking Event************// 
	printf(1,"Hello World.\n");
	sleep(50);
	sleep(10);
	wait();
	//*************  Event end ***********//

	//SYSTEM FUNCTION CALL After OUR EVENT
	intTotalTicks=getAllTicks(intInitialTick);
	intSysCalls=getUserTraps(1); 
	printf(1,"program took\t%d\tticks to execute.\n",intTotalTicks);
	printf(1,"program had\t%d\tsystem calls to execute. \n",intSysCalls);
	 exit();
}


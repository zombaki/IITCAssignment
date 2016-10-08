#include "types.h"
#include "x86.h"
#include "defs.h"
#include "date.h"
#include "param.h"
#include "memlayout.h"
#include "mmu.h"
#include "proc.h"
int syscallnum=-1;//Initialized the variable used for counting the event.
//int trapCallNum=0;//Initialized the variable used for counting the event.
int
sys_fork(void)
{
  return fork();
}

int
sys_exit(void)
{
  exit();
  return 0;  // not reached
}

int
sys_wait(void)
{
  return wait();
}

int
sys_kill(void)
{
  int pid;

  if(argint(0, &pid) < 0)
    return -1;
  return kill(pid);
}

int
sys_getpid(void)
{
  return proc->pid;
}

int
sys_sbrk(void)
{
  int addr;
  int n;

  if(argint(0, &n) < 0)
    return -1;
  addr = proc->sz;
  if(growproc(n) < 0)
    return -1;
  return addr;
}

int
sys_sleep(void)
{
  int n;
  uint ticks0;

  if(argint(0, &n) < 0)
    return -1;
  acquire(&tickslock);
  ticks0 = ticks;
  while(ticks - ticks0 < n){
    if(proc->killed){
      release(&tickslock);
      return -1;
    }
    sleep(&ticks, &tickslock);
  }
  release(&tickslock);
  return 0;
}

// return how many clock tick interrupts have occurred
// since start.
int
sys_uptime(void)
{
  uint xticks;

  acquire(&tickslock);
  xticks = ticks;
  release(&tickslock);
  return xticks;
}
/***************----------------------------**********************
----- Function name sys_getAllTicks
----- I/O Parameter state : This will get either 0 or intial  tick value,
----- 	0: Begining of Program,function will return the ticks value
-----	!=0: End of Program ,in our case anything other then 0,will pass initialvalue returned by the same function
-----				This function will return current tick value removing the start tick value which will be
-----				equals to total ticks.
****************----------------------------**********************/
int
sys_getAllTicks(int initialTicks)
{
	
	
	argint(0,&initialTicks);
	
	if (ticks>initialTicks)
		return (ticks-initialTicks);
	else
		return 0;//This will only return in case of some disruption.

}
/***************----------------------------**********************
----- Function name sys_getUserTraps
----- I/O Parameter state : This will get either 0 or1
----- 	0: Begining of Program,initialize the syscall,so that system can count number of events occur after it.
-----	1: End of Program ,system will return syscallnum
-----	Function will return Number of sysCalls
****************----------------------------**********************/
int
sys_getUserTraps(int intState)
{
	argint(0,&intState);
	if (intState == 0)
	{
		syscallnum=0;
		return 0;
	}
	else
		return syscallnum;
}

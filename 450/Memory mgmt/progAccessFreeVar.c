#include<stdio.h>
#define ARRAYSIZE 100
int main()
{
	int *ptr;
	ptr =(int *)malloc(ARRAYSIZE*sizeof(int));
	 for(int j = 0; j < ARRAYSIZE; j++)
          ptr[j]=j;
	free(ptr);
	printf("%d\n",ptr[10]);
	return 0;
}

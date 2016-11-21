#include<stdio.h>

int main()
{
	int *ptr;
	ptr =(int *)malloc(sizeof(int));
	if(ptr == 0)
	{
		printf("ERROR : Out of memory\n");
		return 1;
	}
	*ptr=2;
	printf("%d\n",*ptr);
	//free(ptr);
	return 0;
}

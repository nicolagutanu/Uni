
#include <stdio.h>
#include <string.h>

struct date
{
    int day, month, year;
}dates[1000];

int main()
{   
    char read_string[100];
    int i = 0;
    int length_months[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    while(scanf("%s", read_string) && strcmp(read_string,"exit") != 0)
    {   
        dates[++i].day = (read_string[0] - '0')*10 + (read_string[1] - '0');
        dates[i].month = (read_string[3] - '0')*10 + (read_string[4] - '0');
        dates[i].year = (read_string[6] - '0')*1000 + (read_string[7] - '0')*100 + (read_string[8] - '0')*10 + (read_string[9] - '0');
    }
    
    for(int j = 1; j <= i; j++)
    {
        if (dates[j].month == 1)
        {
            printf("%d ", dates[j].day);
        }
        else
        {
            int day_in_the_year = 0;
            for (int number_of_month = 0; number_of_month <= dates[j].month - 2; number_of_month++)
            {
                day_in_the_year = day_in_the_year + length_months[number_of_month];
            }
            day_in_the_year = day_in_the_year + dates[j].day;
            if (dates[j].year%4 == 0)
            {
                day_in_the_year += 1;
            }
            printf("%d ", day_in_the_year);
        }
    }
}

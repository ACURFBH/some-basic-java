#include<stdio.h>
#include<iostream>
using namespace std;

int main() {
    int t,n,m,count=0;
    vector<int> a;
    cin >> t;
    for(int i=0; i<t; i++){
        cin >> n;
        for(int k=0; k<n; k++){//build query
            cin >> m;
            a.push_back(m);
        }//query constructed
        m=insertionSort(a, n, count);
        cout << m << endl;//result
        a.clear();
    }
    return 0;
}
void printArray(vector<int> arr, int n){
    for(int i=0; i<n; i++){
        printf("%i ", arr[i]);
    }printf("\n");
}
bool isArranged(vector<int> arr, int n){
    for(int i=1; i<n; i++){
        if(arr[i-1]>arr[i]){
            return false;
        }
    }
    return true;
}
vector<int> shift(vector<int> arr, int back, int front, int &count){//front is ****
    int i, temp=arr[back];
    for(i=back; i>front; i--){
        arr[i]=arr[i-1];    count++;
        //printf("back: ");//debug
        //printArray(arr, back);//debug
        //cout <<"count: "<< count << endl;//debug
    }
    arr[front]=temp;
    return arr;
}
int insertionSort(vector<int> arr, int n, int &count){
    int i,k=0;
    for(i=1; i<n; i++){
        if(arr[i]<arr[i-1]){
            arr=shift(arr, i, k, count);
            //printArray(arr, n);//debug
            k++;
        }else if(isArranged(arr, n)){
            return count;//not k
        }
    }
    //cout << count << endl;//debug
    return count-1;//not k
}
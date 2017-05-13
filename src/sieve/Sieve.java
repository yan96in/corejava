/**
 * 
 */
package sieve;

import java.util.BitSet;

/**
 * This program runs the Sieve of Eratosthenes (素数筛选算法) benchmark. it computes all primes up to 2,000,000.
 * @author yan96in
 *
 */
public class Sieve {
	public static void main(String[] args) {
		int n=2000000;
		long start=System.currentTimeMillis();
		BitSet b=new BitSet(n+1);
		int count=0;
		int i;
		for (i = 0; i <=n; i++) {
			b.set(i);
		}
		i=2;
		while(i*i<n){
			if(b.get(i)){
				count++;
				int k=2*i;
				while(k<=n){
					b.clear(k);
					k+=i;
				}
			}
			i++;
		}
		while(i<=n){
			if(b.get(i))count++;
			i++;
		}
		long end=System.currentTimeMillis();
		System.out.println(count+" primes");
		System.out.println((end-start)+" milliseconds");
	}
}

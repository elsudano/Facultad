/* SumaVectoresC.c
 Suma de dos vectores: v3 = v1 + v2
 Para compilar usar (-lrt: real time library):
 gcc -O2 SumaVectores.c -o SumaVectores -lrt
 Para ejecutar use: SumaVectoresC longitud
 */
#include <stdlib.h> // biblioteca con funciones atoi(), malloc() y free()
#include <stdio.h> // biblioteca donde se encuentra la función printf()
#include <time.h> // biblioteca donde se encuentra la función clock_gettime()
#ifdef _OPENMP
	#include <omp.h> // biblioteca para programas paralelos
#else
	#define omp_get_thread_num() 0
	#define omp_get_num_threads() 1
#endif

#define MAX 33554432 //=2^25
#define PRINT_ALL_MIN 24
// Ponemos que los elementos mínimos para que se
// impriman todas las sumas sea 24 por que atcgrid
// tiene 24 hebras

int main(int argc, char* argv[]) {
	int i;

#ifdef _OPENMP
	double cgt1, cgt2;
#else
	struct timespec cgt1, cgt2;
#endif

	double ncgt, *v1, *v2, *v3;; //para tiempo de ejecución
	unsigned int N, TIME;
	// la variable TIME se usa para imprimir solo el valor
	// del tiempo así es mas fácil copiar desde la consola
	// para realizar las gráficas
	switch (argc){
		case 1:
			printf("Faltan nº componentes del vector\n");
			exit(-1);
			break;
		case 2:
			// Máximo N =2^32-1=4294967295 (sizeof(unsigned int) = 4 B)
			N = atoi(argv[1]);
			TIME = 0;
			break;
		case 3:
			N = atoi(argv[1]);
			TIME = atoi(argv[2]);
			break;
		default:
			printf("La cantidad de parámetros es incorrecta\n");
			exit(-1);
			break;
	}

	v1 = (double*) malloc(N * sizeof(double)); // malloc necesita el tamaño en bytes
	v2 = (double*) malloc(N * sizeof(double)); //si no hay espacio suficiente malloc devuelve NULL
	v3 = (double*) malloc(N * sizeof(double));
	if ((v1 == NULL) || (v2 == NULL) || (v3 == NULL)) {
		printf("Error en la reserva de espacio para los vectores\n");
		exit(-2);
	}

#ifdef _OPENMP
  #pragma omp parallel sections
#endif
{//Inicializar vectores
#ifdef _OPENMP
	#pragma omp section
#endif
	for (i = 0; i < N; i++){
		if (TIME==2){
			printf("thread %d de %d ejecuta la iteración %d del bucle\n",omp_get_thread_num(),omp_get_num_threads(),i);
			printf("V1[%d] = %d * 0.1 + %d * 0.1\n",i,N,i);
		}
		v1[i] = N * 0.1 + i * 0.1;
	}
#ifdef _OPENMP
	#pragma omp section
#endif
	for (i = 0; i < N; i++){
		if (TIME==2){
			printf("thread %d de %d ejecuta la iteración %d del bucle\n",omp_get_thread_num(),omp_get_num_threads(),i);
			printf("V2[%d] = %d * 0.1 - %d * 0.1\n",i,N,i);
		}
		v2[i] = N * 0.1 - i * 0.1; //los valores dependen de N
	}
}

#ifdef _OPENMP
	cgt1 = omp_get_wtime();
#else
	clock_gettime(CLOCK_REALTIME, &cgt1);
#endif

//----------------------------------------------------------------------------
#ifdef _OPENMP
	#pragma omp parallel sections
#endif
{
#ifdef _OPENMP
	#pragma omp section
#endif
//Calcular suma de vectores
	for (i = 0; i < 1*(N/4); i++)
		v3[i] = v1[i] + v2[i];
#ifdef _OPENMP
	#pragma omp section
#endif
	for (i = 1*(N/4); i < 2*(N/4); i++)
		v3[i] = v1[i] + v2[i];
#ifdef _OPENMP
	#pragma omp section
#endif
	for (i = 2*(N/4); i < 3*(N/4); i++)
		v3[i] = v1[i] + v2[i];
#ifdef _OPENMP
	#pragma omp section
#endif
	for (i = 3*(N/4); i < N; i++)
		v3[i] = v1[i] + v2[i];
}
//----------------------------------------------------------------------------

#ifdef _OPENMP
	cgt2 = omp_get_wtime();
#else
	clock_gettime(CLOCK_REALTIME, &cgt2);
#endif

#ifdef _OPENMP
	ncgt = cgt2 - cgt1;
#else
	ncgt = (double) (cgt2.tv_sec - cgt1.tv_sec) + (double) ((cgt2.tv_nsec - cgt1.tv_nsec) / (1.e+9));
#endif
	//Imprimir resultado de la suma y el tiempo de ejecución
	if (N <= PRINT_ALL_MIN){
		if (TIME==1)
			printf("%11.9f\n",ncgt);
		else
			printf("Tiempo(seg.):%11.9f\nTamaño Vectores:%u\n",ncgt,N);

		for(i=0; i<N; i++)
			printf("V1[%d]+V2[%d]=V3[%d](%8.6f+%8.6f=%8.6f)\n",i,i,i,v1[i],v2[i],v3[i]);
	}
	if (TIME==1)
		printf("%11.9f\n",ncgt);
	else
		printf("Tiempo(seg.):%11.9f\nTamaño Vectores:%u\nV1[0]+V2[0]=V3[0](%8.6f+%8.6f=%8.6f)\nV1[%d]+V2[%d]=V3[%d](%8.6f+%8.6f=%8.6f)\n", ncgt,N,v1[0],v2[0],v3[0],N-1,N-1,N-1,v1[N-1],v2[N-1],v3[N-1]);

	free(v1); // libera el espacio reservado para v1
	free(v2); // libera el espacio reservado para v2
	free(v3); // libera el espacio reservado para v3
	return 0;
}

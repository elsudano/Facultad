//package practica.productor_consumidor;
//
//class Buffer 
//{ private int numSlots = 0, cont = 0;   
//  private double[] buffer = null;
//  public Buffer( int p_numSlots ) 
//  { numSlots = p_numSlots ; 
//    buffer = new double[numSlots] ;
//  }
//  public synchronized void depositar( double valor ) throws InterruptedException
//  { while( cont == numSlots ) wait();              
//    buffer[cont] = valor; cont++;      
//    notifyAll();  
//  }
//  public synchronized double extraer() throws InterruptedException
//  { double valor;
//    while( cont == 0 ) wait() ;
//    cont--; valor = buffer[cont] ;
//    notifyAll();
//    return valor;
//  }
//}
//
//class Productor implements Runnable 
//{ private Buffer bb ;
//  int veces; 
//  int numP ;
//  Thread thr ;
//  public Productor( Buffer pbb, int pveces, int pnumP ) 
//  { bb    = pbb;
//    veces = pveces;
//    numP  = pnumP ;
//    thr   = new Thread(this,"productor "+numP);
//  }
//  public void run() 
//  { try
//    { double item = 100*numP ;
//      for( int i=0 ; i<veces ; i++ )
//      { System.out.println(thr.getName()+", produciendo " + item);
//        bb.depositar( item++ );
//      }
//    }
//    catch( Exception e ) 
//    { System.err.println("Excepcion en main: " + e);
//    }
//  }
//}
//
//class Consumidor implements Runnable 
//{ private Buffer bb ;
//  int veces; 
//  int numC ;
//  Thread thr ;
//  public Consumidor( Buffer pbb, int pveces, int pnumC ) 
//  { bb    = pbb;
//    veces = pveces;
//    numC  = pnumC ;
//    thr   = new Thread(this,"consumidor "+numC);
//  }
//  public void run() 
//  { try 
//    { for( int i=0 ; i<veces ; i++ )
//      { double item = bb.extraer ();
//        System.out.println(thr.getName()+", consumiendo "+item);
//      } 
//    }
//    catch( Exception e ) 
//    { System.err.println("Excepcion en main: " + e);
//    }
//  }
//}
//
//
//class EjemploProductorConsumidor 
//{ public static void main( String[] args ) 
//  { if ( args.length != 5 ) 
//    {  System.err.println("Uso: ncons nprod tambuf niterp niterc");
//       return ;
//    }
//    // leer parametros, crear vectores y buffer intermedio
//    Consumidor[] cons = new Consumidor[Integer.parseInt(args[0])] ;
//	  Productor[]  prod = new Productor[Integer.parseInt(args[1])] ;
//	  Buffer buffer = new Buffer(Integer.parseInt(args[2]));
//	  int iter_cons = Integer.parseInt(args[3]);
//	  int iter_prod = Integer.parseInt(args[4]);
//	  if ( cons.length*iter_cons != prod.length*iter_prod )
//	  { System.err.println("no coinciden número de items a producir con a cosumir");
//      return ;
//    }    
//	  // crear hebras
//	  for(int i = 0; i < cons.length; i++) 
//	    cons[i] = new Consumidor(buffer,iter_cons,i) ;
//	  for(int i = 0; i < prod.length; i++)
//	    prod[i] = new Productor(buffer,iter_prod,i) ;
//	  // poner en marcha las hebras
//	  for(int i = 0; i < prod.length; i++) prod[i].thr.start();
//	  for(int i = 0; i < cons.length; i++) cons[i].thr.start();
//  }
//}
//

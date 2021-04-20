package model.data_structures;

public class RBT <K extends Comparable<K>, V extends Comparable<V>> implements ITablaSimbolosOrdenada<K, V>
{
	private class Node
	{
		K key;
		V value;
		Node right;
		Node left;
		int size;
		boolean color;
		
		public Node(K pKey, V pVal, int pSize, boolean pColor)
		{
			key = pKey;
			value = pVal;
			size = pSize;
			color = pColor;
		}
		
	}
	
	public final static boolean RED = true;
	public final static boolean BLACK = false;
	
	public Node root;
	
	public RBT()
	{
		
	}
	
	//auxilio me desmayo
	private boolean isRed(Node rebelde)
	{
		return rebelde==null? BLACK: RED;
	}
	
	//auxilio
	private int size(Node rebelde2)
	{
		return rebelde2==null? 0: rebelde2.size;
	}
	
	@Override
	public int size() 
	{
		return size(root);
	}

	@Override
	public boolean isEmpty() 
	{
		return root==null;
	}

	//el recursivo
	private V get(Node rbd, K key)
	{
		while(rbd!=null)
		{
			int soyLuna = key.compareTo(rbd.key);
			
			if(soyLuna<0)
			{
				rbd = rbd.left;
			}
			else if(soyLuna>0)
				rbd = rbd.right;
			else
				return rbd.value;
		}
		return null;
	}
	
	@Override
	public V get(K key) 
	{
		return get(root, key);
	}

	//especial ese
	private void getHeight(Node violeta, K key)
	{
		
	}
	
	@Override
	public int getHeight(K key) 
	{
		return 0;
	}

	@Override
	public boolean contains(K key) 
	{
		return get(key)!=null;
	}
	
	private void flipColors(Node mannyALaObra)
	{
		mannyALaObra.color=!mannyALaObra.color;
		mannyALaObra.left.color=!mannyALaObra.left.color;
		mannyALaObra.right.color=!mannyALaObra.right.color;
	}
	
	private Node rotateRight(Node jakeLongDRAGON) 
	{
		assert jakeLongDRAGON != null && isRed(jakeLongDRAGON.left);
		
		Node temp = jakeLongDRAGON.left;
		jakeLongDRAGON.left = temp.right;
		temp.right = jakeLongDRAGON;
		temp.color = temp.right.color;
		temp.right.color = RED;
		temp.size = jakeLongDRAGON.size;
		jakeLongDRAGON.size = size(jakeLongDRAGON.left)+size(jakeLongDRAGON.right)+1;
		
		return jakeLongDRAGON;
	}
	
	private Node rotateLeft(Node kimPossible) 
	{
		assert kimPossible != null && isRed(kimPossible.right);
		
		Node temp = kimPossible.right;
		kimPossible.right = temp.left;
		temp.left = kimPossible;
		temp.color = temp.left.color;
		temp.left.color = RED;
		temp.size = kimPossible.size;
		kimPossible.size = size(kimPossible.left)+size(kimPossible.right)+1;
		
		return kimPossible;
	}
	
	private Node put(Node hannahMontana, K key, V val)
	{
		if(hannahMontana == null)
		{
			return new Node(key, val, 1, RED);
		}
		
		int comp = key.compareTo(hannahMontana.key);
		
		if(comp<0)
			hannahMontana.left = put(hannahMontana.left, key, val);
		else if(comp>0)
			hannahMontana.right = put(hannahMontana.right, key, val);
		else
			hannahMontana.value = val;
		
		if(isRed(hannahMontana.right) && !isRed(hannahMontana.left))
			hannahMontana = rotateLeft(hannahMontana);

		if(isRed(hannahMontana.left) && isRed(hannahMontana.left.left))
			hannahMontana = rotateRight(hannahMontana);
		
		if(isRed(hannahMontana.left) && isRed(hannahMontana.right))
			flipColors(hannahMontana);
		
		hannahMontana.size = size(hannahMontana.left)+size(hannahMontana.right)+1;
		
		return hannahMontana;
	}

	@Override
	public void put(K key, V val) 
	{
		//if(val==null)
			//dleete
		root=put(root, key, val);
		root.color=BLACK;
	}

	//special boy
	private int height(Node zackYcody)
	{
		if(zackYcody==null)
		{
			return -1;
		}
		return 1+Math.max(height(zackYcody.left), height(zackYcody.right));
	}
	
	@Override
	public int height()
	{
		return height(root);
	}
	
	private Node keyMin(Node phineas)
	{
		if(phineas.left==null)
			return phineas;
		else
			return keyMin(phineas.left);
	}
	
	@Override
	public K keyMin() 
	{
		return keyMin(root).key;
	}

	private Node keyMax(Node ferb)
	{
		if(ferb.right==null)
			return ferb;
		else
			return keyMin(ferb.right);
	}

	@Override
	public K keyMax() 
	{
		return keyMax(root).key;
	}

	@Override
	public IListaTad<K> keySet() 
	{
		if (isEmpty())
		{
			return new ArregloDinamico<>(7);
		}
		return keysInRange(keyMin(), keyMax());
	}

	@Override
	public IListaTad<V> valueSet() 
	{
		if (isEmpty())
		{
			return new ArregloDinamico<>(7);
		}
		return valuesInRange(keyMin(), keyMax());
	}

	private void keysInRange(Node auxilio, IListaTad<K> arreglo, K lowKey, K highKey)
	{
		int cmpLow = lowKey.compareTo(auxilio.key);
		int cmpHigh=highKey.compareTo(auxilio.key);
		
		if(cmpLow<0)
			keysInRange(auxilio.left, arreglo, lowKey,highKey);
		
		if(cmpLow<=0 && cmpHigh>=0)
			arreglo.addLast(auxilio.key);
		
		if(cmpHigh>0)
			keysInRange(auxilio.right, arreglo, lowKey,highKey);
	}
	
	@Override
	public IListaTad<K> keysInRange(K keyOrigin, K keyEnd) 
	{
		IListaTad<K> resp = new ArregloDinamico<>(7);
		keysInRange(root, resp, keyOrigin, keyEnd);
		return resp;
	}

	
	private void valuesInRange(Node auxilio, IListaTad<V> resp, K lowKey, K highKey)
	{
		int cmpLow = lowKey.compareTo(auxilio.key);
		int cmpHigh=highKey.compareTo(auxilio.key);
		
		if(cmpLow<0)
			valuesInRange(auxilio.left, resp, lowKey,highKey);
		
		if(cmpLow<=0 && cmpHigh>=0)
			resp.addLast(auxilio.value);
		
		if(cmpHigh>0)
			valuesInRange(auxilio.right, resp, lowKey,highKey);
	}
	@Override
	public IListaTad<V> valuesInRange(K keyOrigin, K keyEnd) 
	{
		IListaTad<V> resp = new ArregloDinamico<>(7);
		valuesInRange(root, resp, keyOrigin, keyEnd);
		return resp;
	}

}

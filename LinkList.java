
public class LinkList
{
	private Link first;            // ref to first link on list

	//-------------------------------------------------------------
	public LinkList()              // constructor
	{
		first = null;               // no links on list yet
	}
	//-------------------------------------------------------------
	public void insertFirst(Card card)
	{                           // make new link
		Link newLink = new Link(card);
		newLink.next = first;       // it points to old first link
		first = newLink;            // now first points to this
	}
	public void add(Card card)
	{                           // make new link
		Link newLink = new Link(card);
		newLink.next = first;       // it points to old first link
		first = newLink;            // now first points to this
	}
	//-------------------------------------------------------------
	public Link find(Card cardToFind)      // find link with given key
	{                           // (assumes non-empty list)
		Link current = first;              // start at 'first'
		while(current != null && !current.cardLink.equals(cardToFind))        // while no match,
		{
			current = current.next;      // go to next link
		}
		return current;                    // found it or null if not found
	}
	//-------------------------------------------------------------
	public Link delete(Card cardToFind)    // delete link with given key
	{                           // (assumes non-empty list)
		Link current = first;              // search for link
		Link previous = null;
		while(current != null && !current.cardLink.equals(cardToFind))
		{
			previous = current;          // go to next link
			current = current.next;
		}
		if(current == null)               // didn't find it
			return null;

		if(previous == null)              // if first link,
			first = first.next;           //    change first
		else                               // otherwise,
			previous.next = current.next; //    bypass it
		return current;
	}
	//-------------------------------------------------------------
	public void displayList()      // display the list
	{
		System.out.print("List (first-->last): ");
		Link current = first;       // start at beginning of list
		while(current != null)      // until end of list,
		{
			current.displayLink();   // print data
			current = current.next;  // move to next link
		}
		System.out.println("");
	}
	//-------------------------------------------------------------

	//-------------------------------------------------------------
	public Card getFirst()    // delete link with given key
	{                           // (assumes non-empty list)
		if (first == null) {
			return null; // return null if list is empty
		}
		Link current = first;              // get first link
		first = first.next;             //    change first
		return current.cardLink;
	}
}  // end class LinkList
////////////////////////////////////////////////////////////////
/*class LinkedLists
{
	public static void main(String[] args)
	{
		LinkList theList = new LinkList();  // make list

		theList.insertFirst(new Card("heart", "ace", 11,"ah.gif"));      // insert 4 items
		theList.insertFirst(new Card("Spade", "ace", 11,"as.gif"));
		//theList.insertFirst(66, 6.99);
		//theList.insertFirst(88, 8.99);

		theList.displayList();              // display list

		Link f = theList.find(new Card("heart", "ace", 11,"ah.gif"));          // find item
		if( f != null)
			System.out.println("Found link with key " + f.cardLink);
		else
			System.out.println("Can't find link");

		Link d = theList.delete(new Card("heart", "ace", 11,"ah.gif"));        // delete item
		if( d != null )
			System.out.println("Deleted link with key " + d.cardLink);
		else
			System.out.println("Can't delete link");

		theList.displayList();              // display list
	}  // end main()
}  // end class LinkList2App
////////////////////////////////////////////////////////////////
/// */

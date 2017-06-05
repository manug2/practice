Practice solutions from various places:
HR
EPI
Extras:
1. ATM - Cash dispensing logic.
2. Leisure Traveller - Given pairs of airports, find the longest series of connected airports a traveller can travel non-stop.
3. Given two large log files, with errors from an old and new version of application. Find:
    a. Errors that got solved in new version.
    b. Errors that got introduced in new version.
4. Multi-threaded debit/credit transaction processing.
5. Multi-threaded priority queue - using skip list.
6. Rolling hash string matching, xml tag matching.
7. Distributed cache.
9. Multi-threaded priority queue - by breaking priority into ranges and using separate list for each range.
    The first chunk is implemented as an immutable array and extract_min is implemented using atomic increment and get operation.
    Inserts into first chunk are delayed causing excellent amortized performance.
    http://www.cs.technion.ac.il/~erez/Papers/cbpq-paper-l.pdf
    Also see for performance analysis technique http://www.mit.edu/~jerryzli/SprayList-CR.pdf
    

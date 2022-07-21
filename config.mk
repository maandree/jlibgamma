PREFIX = /usr
JAVADIR = $(PREFIX)/share/java

INSTALLED_JAVAPATH = $$(dirname $$(dirname $$($(REALPATH) $$(which $(JAVAC) || type -p $(JAVAC)))))

REALPATH = readlink -f
CC       = c99
JAVA     = java
JAVAC    = javac
JAVADOC  = javadoc
JAR      = jar
JAVAH    = javah
# The Makefile will check whether $(JAVAH) exists, otherwise it will use $(JAVAC) with -h

CPPFLAGS = -D_DEFAULT_SOURCE -D_BSD_SOURCE -D_XOPEN_SOURCE=700 -D_GNU_SOURCE\
           -isystem "$(INSTALLED_JAVAPATH)/include"\
           -isystem "$(INSTALLED_JAVAPATH)/include/$$(uname | tr '[A-Z]' '[a-z]')"
CFLAGS   = -Wall -O2 $(OS_CFLAGS) $(CPPFLAGS)
LDFLAGS  = -lgamma $(OS_LDFLAGS)

# Copying and distribution of this file, with or without modification,
# are permitted in any medium without royalty provided the copyright
# notice and this notice are preserved.  This file is offered as-is,
# without any warranty.


# The package path prefix, if you want to install to another root, set DESTDIR to that root
PREFIX ?= /usr
# The library path excluding prefix
LIB ?= /lib
# The resource path excluding prefix
DATA ?= /share
# The library path including prefix
LIBDIR ?= $(PREFIX)$(LIB)
# The resource path including prefix
DATADIR ?= $(PREFIX)$(DATA)
# The generic documentation path including prefix
DOCDIR ?= $(DATADIR)/doc
# The info manual documentation path including prefix
INFODIR ?= $(DATADIR)/info
# The license base path including prefix
LICENSEDIR ?= $(DATADIR)/licenses

# The name of the package as it should be installed
PKGNAME = jlibgamma


# The Java compiler
JAVAC = javac
# The Java archive creator
JAR = jar
# The JNI header generator
JAVAH = javah


# The version of the library.
LIB_MAJOR = 1
LIB_MINOR = 0
LIB_VERSION = $(LIB_MAJOR).$(LIB_MINOR)

# The so in libgammamm.so as the library file is named on Linux
ifeq ($(PLATFORM),w32)
SO = dll
else
ifeq ($(PLATFORM),osx)
SO = dylib
else
SO = so
endif
endif

# Platform dependent flags
ifeq ($(PLATFORM),w32)
SHARED = -mdll
LDSO = -Wl,-soname,jlibgamma.$(SO).$(LIB_MAJOR)
PIC =
else
ifeq ($(PLATFORM),osx)
SHARED = -dynamiclib
LDSO =
PIC = -fPIC
else
SHARED = -shared
LDSO = -Wl,-soname,jlibgamma.$(SO).$(LIB_MAJOR)
PIC = -fPIC
endif
endif

# The C standard for C code compilation
STD = c99
# Optimisation settings for C code compilation
C_OPTIMISE ?= -Og -g
# Optimisation settings for Java code compilation
JAVA_OPTIMISE ?= -O

# Warning flags for C code, set to empty if you are not using GCC
C_WARN = -Wall -Wextra -pedantic
## TODO add more warnings

# Warning flags for Java code.
JAVA_WARN = -Xlint:all



# Flags to use when compiling C code
CC_FLAGS = -std=$(STD) $(C_OPTIMISE) $(CFLAGS) $(PIC) $(CPPFLAGS) $(WARN)

# Flags to use when linking native objects
LD_FLAGS = -lgamma -std=$(STD) $(C_OPTIMISE) $(LDFLAGS) $(WARN)

# Flags to use when compiling Java code
JAVAC_FLAGS = $(JAVACFLAGS) $(JAVA_OPTIMISE) $(JAVA_WARN)


# Java classes
RAMPS = Ramp16 Ramp32 Ramp64 Ramp8 Rampd Rampf
SIMPLE = AdjustmentMethodCapabilities ConnectorType LibgammaException Ramp SubpixelOrder
JAVA_OBJ = AdjustmentMethod CRTC CRTCInformation GammaRamps Libgamma Partition Site $(RAMPS) $(SIMPLE)


.PHONY: all
all: java

.PHONY: java
java: $(foreach O,$(JAVA_OBJ),obj/libgamma/$(O).class)

obj/libgamma/%.class: src/libgamma/%.java %
	@mkdir -p obj/libgamma
	$(JAVAC) $(JAVAC_FLAGS) -cp src -s src -d obj $<

# Dependencies
.PHONY: AdjustmentMethod CRTCInformation CRTC GammaRamps Libgamma Partition Site $(RAMPS) $(SIMPLE)
Libgamma:
AdjustmentMethod: $(foreach C, Libgamma AdjustmentMethodCapabilities                  ,obj/libgamma/$(C).class)
Site:             $(foreach C, Libgamma LibgammaException AdjustmentMethod            ,obj/libgamma/$(C).class)
Partition:        $(foreach C, Libgamma LibgammaException Site                        ,obj/libgamma/$(C).class)
CRTC:             $(foreach C, Libgamma LibgammaException Partition CRTCInformation   ,obj/libgamma/$(C).class)
CRTCInformation:  $(foreach C, Libgamma LibgammaException SubpixelOrder ConnectorType ,obj/libgamma/$(C).class)
GammaRamps:       $(foreach C, Libgamma LibgammaException Ramp $(RAMPS)               ,obj/libgamma/$(C).class)
$(RAMPS):         $(foreach C, Libgamma Ramp                                          ,obj/libgamma/$(C).class)
$(SIMPLE):        $(foreach C, Libgamma                                               ,obj/libgamma/$(C).class)



.PHONY: clean
clean:
	-rm -r obj bin


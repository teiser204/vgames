from django.db import models
from vgames import settings

# --------------------------------------------------------------------
# feature
# --------------------------------------------------------------------
class Feature(models.Model):
    desc            = models.CharField(max_length=100, verbose_name="Περιγραφή Χαρακτηριστικού")
    created_at      = models.DateTimeField(auto_now_add=True, null=True)
    updated_at      = models.DateTimeField(auto_now=True, null=True)

    def __str__(self):
        return self.desc 

    class Meta:
        db_table = 'feature'   
        verbose_name = 'Χαρακτηριστικό'
        verbose_name_plural = 'Χαρακτηριστικά' 
        ordering = ['desc']


# --------------------------------------------------------------------
# platform
# --------------------------------------------------------------------
class Platform(models.Model):
    desc            = models.CharField(max_length=100, verbose_name="Περιγραφή πλατφόρμας")
    created_at      = models.DateTimeField(auto_now_add=True, null=True)
    updated_at      = models.DateTimeField(auto_now=True, null=True)

    def __str__(self):
        return self.desc 

    class Meta:
        db_table = 'platform'   
        verbose_name = 'Πλατφόρμα'
        verbose_name_plural = 'Πλατφόρμες' 
        ordering = ['desc']


# --------------------------------------------------------------------
# language
# --------------------------------------------------------------------
class Language(models.Model):
    desc            = models.CharField(max_length=100, verbose_name="Περιγραφή γλώσσας")
    created_at      = models.DateTimeField(auto_now_add=True, null=True)
    updated_at      = models.DateTimeField(auto_now=True, null=True)

    def __str__(self):
        return self.desc 

    class Meta:
        db_table = 'language'   
        verbose_name = 'Γλώσσα'
        verbose_name_plural = 'Γλώσσες' 
        ordering = ['desc']        


# --------------------------------------------------------------------
# genre
# --------------------------------------------------------------------
class Genre(models.Model):
    desc            = models.CharField(max_length=100, verbose_name="Περιγραφή κατηγορίας")
    created_at      = models.DateTimeField(auto_now_add=True, null=True)
    updated_at      = models.DateTimeField(auto_now=True, null=True)

    def __str__(self):
        return self.desc 

    class Meta:
        db_table = 'genre'   
        verbose_name = 'Κατηγορία'
        verbose_name_plural = 'Κατηγορίες' 
        ordering = ['desc']      


# --------------------------------------------------------------------
# company
# --------------------------------------------------------------------
class Company(models.Model):
    desc            = models.CharField(max_length=100, verbose_name="Περιγραφή εταιρίας")
    created_at      = models.DateTimeField(auto_now_add=True, null=True)
    updated_at      = models.DateTimeField(auto_now=True, null=True)

    def __str__(self):
        return self.desc 

    class Meta:
        db_table = 'company'   
        verbose_name = 'Εταιρία'
        verbose_name_plural = 'Εταιρίες' 
        ordering = ['desc']   


# --------------------------------------------------------------------
# game
# --------------------------------------------------------------------
class Game(models.Model):
    company         = models.ForeignKey(Company, on_delete=models.PROTECT, related_name="games", verbose_name="Εταιρία")
    title           = models.CharField(max_length=255, verbose_name='Τίτλος παιχνιδιού')
    desc            = models.TextField(blank=False, verbose_name='Περιγραφή')
    year            = models.PositiveSmallIntegerField(blank=False, null=False, verbose_name='Έτος έκδοσης')
    price           = models.DecimalField(max_digits=10, decimal_places=2, blank=False, null=False, verbose_name='Τιμή')
    rating          = models.DecimalField(max_digits=3, decimal_places=2, blank=False, null=False, verbose_name='Βαθμολογία')
    url             = models.CharField(max_length=255, verbose_name='Ιστότοπος παιχνιδιού')
    image           = models.ImageField(default="default.png", upload_to="game_images", verbose_name='Εικόνα παιχνιδιού')

    created_at      = models.DateTimeField(auto_now_add=True, null=True)
    updated_at      = models.DateTimeField(auto_now=True, null=True)

    # many-to-many relationships
    features        = models.ManyToManyField(Feature, related_name="games", verbose_name="Χαρακτηριστικά", db_table="game_feature")
    platforms       = models.ManyToManyField(Platform, related_name="games", verbose_name="Πλατφόρμες", db_table="game_platform")
    languages       = models.ManyToManyField(Language, related_name="games", verbose_name="Γλώσσες", db_table="game_language")
    genres          = models.ManyToManyField(Genre, related_name="games", verbose_name="Κατηγορίες", db_table="game_genre")

    def __str__(self):
        return self.title 

    class Meta:
        db_table = 'game'   
        verbose_name = 'Παιχνίδι'
        verbose_name_plural = 'Παιχνίδια' 
        ordering = ['title']  


# --------------------------------------------------------------------
# gallery
# --------------------------------------------------------------------
class Gallery(models.Model):
    game            = models.ForeignKey(Game, on_delete=models.CASCADE, related_name="gallery", verbose_name="Παιχνίδι")
    image           = models.ImageField(default="default.png", upload_to="game_images", verbose_name='Εικόνα παιχνιδιού')

    def __str__(self):
        return self.game.title + " (" + str(self.id) + ")"

    class Meta:
        db_table = 'gallery'   
        verbose_name = 'Εικόνα'
        verbose_name_plural = 'Εικόνες' 



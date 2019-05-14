from django.shortcuts import get_object_or_404
from rest_framework import status
from rest_framework.decorators import APIView, api_view
from rest_framework.response import Response
from rest_framework import generics
from django_filters import rest_framework as filters
from django.http import Http404
from .filters import LimitFilterBackend
from django.contrib.auth.models import User

from .serializers import (
    FeatureSerializer, PlatformSerializer, LanguageSerializer, 
    GenreSerializer, CompanySerializer, GameListSerializer, 
    GameDetailsSerializer, GameCreateSerializer, GameUpdateSerializer,
    UserSerializer
)
from games.models import (
    Feature, Platform, Language, Genre, Company, Game
)
from games.api.filters import GameFilter



# --------------------------------------------------------------------
# Feature API views
# --------------------------------------------------------------------
class FeatureList(generics.ListCreateAPIView):
    queryset = Feature.objects.all()
    serializer_class = FeatureSerializer


class FeatureDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Feature.objects.all()
    serializer_class = FeatureSerializer    


# --------------------------------------------------------------------
# Platform API views
# --------------------------------------------------------------------
class PlatformList(generics.ListCreateAPIView):
    queryset = Platform.objects.all()
    serializer_class = PlatformSerializer


class PlatformDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Platform.objects.all()
    serializer_class = PlatformSerializer  

    
# --------------------------------------------------------------------
# Language API views
# --------------------------------------------------------------------
class LanguageList(generics.ListCreateAPIView):
    queryset = Language.objects.all()
    serializer_class = LanguageSerializer


class LanguageDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Language.objects.all()
    serializer_class = LanguageSerializer 


# --------------------------------------------------------------------
# Genre API views
# --------------------------------------------------------------------
class GenreList(generics.ListCreateAPIView):
    queryset = Genre.objects.all()
    serializer_class = GenreSerializer


class GenreDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Genre.objects.all()
    serializer_class = GenreSerializer


# --------------------------------------------------------------------
# Company API views
# --------------------------------------------------------------------
class CompanyList(generics.ListCreateAPIView):
    queryset = Company.objects.all()
    serializer_class = CompanySerializer


class CompanyDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Company.objects.all()
    serializer_class = CompanySerializer


# --------------------------------------------------------------------
# Game API views
# --------------------------------------------------------------------
class GameList(generics.ListCreateAPIView):
    queryset = Game.objects.all()
    #serializer_class = GameListSerializer
    filterset_class = GameFilter

    def get_serializer_class(self):
        if self.request.method == "GET":
            return GameListSerializer
        else:
            return GameCreateSerializer


class GameDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Game.objects.all()
    #serializer_class = GameDetailsSerializer

    def get_serializer_class(self):
        if self.request.method == "PUT" or self.request.method == "UPDATE":
            return GameUpdateSerializer
        else:
            return GameDetailsSerializer


# --------------------------------------------------------------------
# User API views
# --------------------------------------------------------------------
class UserList(generics.ListAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer

class UserDetail(generics.RetrieveAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer    